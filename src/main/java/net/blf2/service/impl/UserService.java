package net.blf2.service.impl;

import net.blf2.dao.IUserDao;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.UserInfo;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by blf2 on 17-2-4.
 */
@Service("UserService")
public class UserService implements IUserService {

    @Resource
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public UserInfo findUserInfoByUserId(String userId) {
        return userDao.queryUserInfoById(userId);
    }

    public List<UserInfo> findAllUserInfos() {
        return userDao.queryUserInfoAll();
    }

    public boolean registerUserInfo(UserInfo userInfo) {
        try {
            userDao.insertUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        try {
            userDao.updateUserInfo(userInfo);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    //TODO:delete all infomation of user infomation
    public boolean deleteUserInfoByUserId(String userId) {
        return false;
    }

    public UserInfo checkLoginInfo(String userNum, String userPswd) throws Exception{
        UserInfo userInfo = null;
        try {
            userInfo = userDao.queryUserInfoByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Consts.DATABASE_RETURN_ERROR);
        }
        if(userInfo != null && userPswd.equals(userInfo.getUserPswd()))
            return userInfo;
        return null;
    }

    public boolean checkUserPhoneHasExist(String userPhone) throws Exception{
        String userId = null;
        try {
            userId = userDao.queryUserIdByUserPhone(userPhone);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Consts.DATABASE_RETURN_ERROR);
        }
        return  userId != null;
    }

    public boolean checkUserNumHasExist(String userNum) throws Exception{
        String userId = null;
        try {
            userId = userDao.queryUserIdByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception(Consts.DATABASE_RETURN_ERROR);
        }
        return  userId != null;
    }

    public List<UserInfo> getClassMatesByUserGrade(String userGrade) {
        List<UserInfo> userInfos = null;
        try {
            userInfos = userDao.queryUserInfoByUserGrade(userGrade);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return userInfos == null || userInfos.size() == 0 ? null : userInfos;
    }

    public List<Map<String,Object>> getClassMatesScoreDetail(String userGrade) {
        List<UserInfo> userInfos = this.getClassMatesByUserGrade(userGrade);
        List<Map<String,Object>> mapUserDetailList = new LinkedList<Map<String, Object>>();
        for(UserInfo userInfo : userInfos){
            Map<String,Object> queryMap = new HashMap<String, Object>();
            queryMap.put(Consts.MONGO_PRIMARY_KEY_NAME,userInfo.getUserId());
            Document document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS, queryMap);
            if(document != null && !document.isEmpty()){
                document.put(Consts.USER_INFO_NAME,userInfo);
                mapUserDetailList.add(document);
            }
        }
        return mapUserDetailList;
    }

    public boolean mongoAddClassMateScoreDetail(Map<String, Object> userScoreDetailMap) {
        return MongoOperator.insertDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,userScoreDetailMap);
    }

    public boolean mongoUpdateClassMatesScoreDetail(Map<String, Object> userScoreDetailMap) {
        return MongoOperator.updateDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,userScoreDetailMap);
    }

    public UserInfo findUserInfoByUserNum(String userNum) throws Exception{
        UserInfo userInfo = null;
        try {
            userInfo = userDao.queryUserInfoByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            throw new Exception("数据库检查出错！");
        }
        return userInfo;
    }

    public Map<String, Object> MongoFindUserScoreDetailByUserId(String userId) {
        Map<String,Object> queryMap = new HashMap<String, Object>();
        queryMap.put(Consts.MONGO_PRIMARY_KEY_NAME,userId);
        Document document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,
                queryMap);
        return document;
    }

    public void generateExcel(String userGrade) throws Exception{
        List<UserInfo> userInfoList = getClassMatesByUserGrade(userGrade);
        String fileName = Tools.class.getClassLoader().getResource("").getPath()+Consts.EXCEL_FILE_PATH+"/"+userGrade+".xls";
        System.out.println(fileName);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        int index = 1;
        Row row;
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("学号");
        row.createCell(1).setCellValue("加减分明细");
        row.createCell(2).setCellValue("合计");
        for(UserInfo iUserInfo : userInfoList){
            if(Consts.ADMIN_ROLE_NAME.equals(iUserInfo.getUserRole().getRoleName()))
                continue;
            Map<String,Object>scoreMap = this.MongoFindUserScoreDetailByUserId(iUserInfo.getUserId());
            String str = "";
            if(scoreMap != null) {
                for (Map.Entry<String, Object> entry : scoreMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (!("_id".equals(key) || Consts.USER_SUM_SCORE.equals(key))) {
                        str += (key + " " + value + "\n");
                    }
                }
                System.out.println(str);
            }
            row = sheet.createRow(index++);
            row.createCell(0).setCellValue(iUserInfo.getUserNum());
            row.createCell(1).setCellValue((str));
            Double sum = scoreMap != null ? (Double)scoreMap.get(Consts.USER_SUM_SCORE) : 0.0;
            row.createCell(2).setCellValue(sum);
        }
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        File file = new File(fileName);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        wb.write(fileOutputStream);
        wb.close();
    }
}
