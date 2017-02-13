package net.blf2.service.impl;

import net.blf2.dao.IUserDao;
import net.blf2.dao.MongoOperator;
import net.blf2.entity.UserInfo;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import org.bson.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
            return false;
        }
        return true;
    }

    public boolean updateUserInfo(UserInfo userInfo) {
        try {
            userDao.updateUserInfo(userInfo);
        }catch (Exception ex){
            return false;
        }
        return true;
    }
    //TODO:delete all infomation of user infomation
    public boolean deleteUserInfoByUserId(String userId) {
        return false;
    }

    public UserInfo checkLoginInfo(String userNum, String userPswd) {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.queryUserInfoByUserNum(userNum);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        if(userInfo != null && userPswd.equals(userInfo.getUserPswd()))
            return userInfo;
        return null;
    }

    public boolean checkUserPhoneHasExist(String userPhone) {
        return userDao.queryUserIdByUserPhone(userPhone) != null;
    }

    public boolean checkUserNumHasExist(String userNum) {
        return userDao.queryUserIdByUserNum(userNum) != null;
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

    public boolean addClassMateScoreDetail(Map<String, Object> userScoreDetailMap) {
        return false;
    }

    public boolean updateClassMatesScoreDetail(Map<String, Object> userScoreDetailMap) {
        return false;
    }

}
