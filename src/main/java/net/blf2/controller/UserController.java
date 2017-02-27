package net.blf2.controller;

import javafx.beans.binding.ObjectExpression;
import net.blf2.entity.*;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.windows.ThemeReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-13.
 */
@Controller("UserController")
@RequestMapping("/User")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private IClassService classService;

    private static boolean isCheckAdmin = false;

    private static Map<Integer,String> userGradeNameValues;

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registerMonitor",method = {RequestMethod.POST})
    public String registerMonitorInfo(HttpSession httpSession,
                                      @RequestParam(value = "userNum",required = true)String userNum,
                                      @RequestParam(value = "userPswd",required = true) String userPswd,
                                      @RequestParam(value = "userPhone",required = true)String userPhone,
                                      String userNote,String classNote,
                                      @RequestParam(value = "invitationCode",required = true)String invitationCode,
                                      @RequestParam(value = "majorName",required = true)String majorName,
                                      @RequestParam(value = "classGrade",required = true)String classGrade,
                                      @RequestParam(value = "classNum",required = true)String classNum) throws Exception{
        try {
            if(!Tools.checkInvitationCode(invitationCode,userNum)){
                httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,"验证码错误，请重新输入，如果多次出现，请联系管理员！");
                return "error";
            }
        }catch (Exception ex){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE, "验证出错，请联系管理员！");
            throw new Exception();
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setUserNum(userNum);
        userInfo.setUserPswd(userPswd);
        userInfo.setUserNote(userNote);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserGrade(majorName + classGrade + classNum);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setRoleId(Consts.MONITOR_ROLE_ID);
        userRoleInfo.setRoleName(Consts.MONITOR_ROLE_NAME);
        userInfo.setUserRole(userRoleInfo);
        ClassInfo classInfo = new ClassInfo();
        classInfo.setMonitorInfo(userInfo);
        classInfo.setClassGrade(classGrade);
        classInfo.setClassNum(classNum);
        classInfo.setMajorName(majorName);
        classInfo.setClassId(UUID.randomUUID().toString());
        classInfo.setClassNote(classNote);
        Map<String,Object> itemNameValueMap = new HashMap<String, Object>();
        itemNameValueMap.put(Consts.USER_SUM_SCORE, 0.0);
        itemNameValueMap.put(Consts.MONGO_PRIMARY_KEY_NAME, userInfo.getUserId());
        userService.mongoAddClassMateScoreDetail(itemNameValueMap);
        if(userService.registerUserInfo(userInfo) && classService.registerClassInfo(classInfo)) {
            httpSession.setAttribute(Consts.LOGIN_INFO,userInfo);
            return this.redirectPage(userRoleInfo, httpSession);
        }
        throw new Exception();
    }
    @RequestMapping(value = "/submitInfo",method = RequestMethod.POST)
    public String submitInfo(UserInfo userInfo,ItemsInfoForm itemsInfoForm,
                             @RequestParam(value = "validateCode",required = true)String validateCode,
                             HttpSession httpSession) throws Exception{
        UserInfo findUserInfo = null;
        try {
            findUserInfo = userService.findUserInfoByUserNum(userInfo.getUserNum());
        }catch (Exception ex){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,ex.getMessage());
            throw new Exception();
        }
        if(findUserInfo != null){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,"您的学号已经注册！！！");
            return "error";
        }
        String userGradeTrs = userGradeNameValues.get(Integer.parseInt(userInfo.getUserGrade())) != null ? userGradeNameValues.get(Integer.parseInt(userInfo.getUserGrade())) : "default";
        if(!Tools.checkInvitationCode(validateCode,userGradeTrs)){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,"验证码错误，请向班长索要正确验证码。");
            return "error";
        }
            UserRoleInfo userRoleInfo = new UserRoleInfo();
            userRoleInfo.setRoleId(Consts.PRIMARY_ROLR_ID);
            userRoleInfo.setRoleName(Consts.PRIMARY_ROLE_NAME);
            userInfo.setUserRole(userRoleInfo);
            userInfo.setUserId(UUID.randomUUID().toString());
            userInfo.setUserGrade(userGradeTrs);
            userService.registerUserInfo(userInfo);
            findUserInfo = userInfo;
        double sum = 0.0;
        Map<String,Object> itemNameValueMap = new HashMap<String, Object>();
        List<ItemsInfo> itemsInfoList = itemsInfoForm != null ? itemsInfoForm.getItemsInfoList() : null;
        if(itemsInfoList != null) {
            for (ItemsInfo itemsInfo : itemsInfoList) {
                if(itemsInfo != null && itemsInfo.getItemName() != null && itemsInfo.getItemValue() != null) {
                    itemNameValueMap.put(itemsInfo.getItemName(), itemsInfo.getItemValue());
                    sum += itemsInfo.getItemValue();
                }
            }
        }
        itemNameValueMap.put(Consts.USER_SUM_SCORE, sum);
        itemNameValueMap.put(Consts.MONGO_PRIMARY_KEY_NAME, findUserInfo.getUserId());
        userService.mongoAddClassMateScoreDetail(itemNameValueMap);
        httpSession.setAttribute(Consts.LOGIN_INFO,findUserInfo);
        return this.redirectPage(userInfo.getUserRole(),httpSession);
    }
    @RequestMapping(value = "/updateScore",method = RequestMethod.POST)
    public String updateScore(HttpSession httpSession,UserInfo userInfo,ItemsInfoForm itemsInfoForm){
        if(itemsInfoForm != null && userInfo != null){
            UserInfo loginInfo = (UserInfo) httpSession.getAttribute(Consts.LOGIN_INFO);
            userInfo.setUserRole(loginInfo.getUserRole());
            Map<String,Object>scoreDetailMap = new HashMap<String, Object>();
            scoreDetailMap.put(Consts.MONGO_PRIMARY_KEY_NAME, userInfo.getUserId());
            Double sum = 0.0;
            List<ItemsInfo> itemsInfoList = itemsInfoForm != null ? itemsInfoForm.getItemsInfoList() : null;
            if(itemsInfoList != null) {
                for (ItemsInfo itemsInfo : itemsInfoList) {
                    if(itemsInfo != null && itemsInfo.getItemName() != null && itemsInfo.getItemValue() != null) {
                        scoreDetailMap.put(itemsInfo.getItemName(), itemsInfo.getItemValue());
                        sum += itemsInfo.getItemValue();
                    }
                }
            }
            scoreDetailMap.put(Consts.USER_SUM_SCORE, sum);
            userService.updateUserInfo(userInfo);
            userService.mongoUpdateClassMatesScoreDetail(scoreDetailMap);
            return this.redirectPage(userInfo.getUserRole(),httpSession);
        }
        httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE, "未知错误!!!");
        return "error";
    }
    @RequestMapping(value = "/findUserInfoAndScore",method = RequestMethod.POST)
    public String findUserInfoAndScore(@RequestParam(value = "userNum",required = true)String userNum,
                                       @RequestParam(value = "userPswd",required = true)String userPswd,
                                       HttpSession httpSession) throws Exception{
        UserInfo userInfo = userService.checkLoginInfo(userNum,userPswd);
        if(userInfo != null){
            httpSession.setAttribute(Consts.LOGIN_INFO, userInfo);
            Map<String,Object>scoreMap = userService.MongoFindUserScoreDetailByUserId(userInfo.getUserId());
            httpSession.setAttribute(Consts.FRONT_SCORE_MAP, scoreMap);
            List<String> majorNameGradeNumList = classService.findMajorNameGradeNumsAll();
            majorNameGradeNumList.remove(userInfo.getUserGrade());
            httpSession.setAttribute(Consts.FRONT_GRADE_ALL, majorNameGradeNumList);
            return "findUserInfoPage";
        }
        httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,"用户名或者密码错误。");
        return "error";
    }
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(@RequestParam(value = "userNum",required = true)String userNum,
                        @RequestParam(value = "userPswd",required = true)String userPswd,
                        HttpSession httpSession) throws Exception{
        if(!isCheckAdmin)
            checkAdmin();
        UserInfo userInfo = null;
        if((userInfo = userService.checkLoginInfo(userNum,userPswd)) != null){
            httpSession.setAttribute(Consts.LOGIN_INFO,userInfo);
            return redirectPage(userInfo.getUserRole(),httpSession);
        }
        return redirectPage(null,null);
    }
    @RequestMapping(value = "/generateValidateCode",method = {RequestMethod.POST})
    public String generateValidateCode(HttpSession httpSession,String userNum) throws Exception{
        String validateCode = Tools.generateInvitationCode(userNum);
        httpSession.setAttribute(Consts.VALIDATE_CODE,validateCode);
        return redirectPage(((UserInfo)httpSession.getAttribute(Consts.LOGIN_INFO)).getUserRole(),httpSession);
    }
    @RequestMapping("/toSignin")
    public String toSignin(){
        return "signin";
    }
    @RequestMapping("/toMonitorRegister")
    public String toMonitorRegister(){
        return "monitorRegister";
    }
    @RequestMapping("toCreateClassInfo")
    public String toCreateClassInfo(){
        return "createClassInfo";
    }
    @RequestMapping("/toSubmitInfo")
    public String toSubmitInfo(HttpSession httpSession){
        List<String>majorNameGradeNumsAll = classService.findMajorNameGradeNumsAll();
        userGradeNameValues = new HashMap<Integer, String>();
        int index = 1;
        for(String str : majorNameGradeNumsAll){
            userGradeNameValues.put(index,str);
            index++;
        }
        httpSession.setAttribute(Consts.USER_GRADE_NAME_VALUE,userGradeNameValues);
        return "submitInfo";
    }
    @RequestMapping("/toUpdateScore")
    public String toUpdateScore(HttpSession httpSession){
        UserInfo userInfo = (UserInfo)httpSession.getAttribute(Consts.LOGIN_INFO);
        if(userInfo != null) {
            Map<String, Object> scoreMap = userService.MongoFindUserScoreDetailByUserId(userInfo.getUserId());
            httpSession.setAttribute(Consts.FRONT_SCORE_MAP, scoreMap);
            List<String>majorNameGradeNumsAll = classService.findMajorNameGradeNumsAll();
            userGradeNameValues = new HashMap<Integer, String>();
            int index = 1;
            for(String str : majorNameGradeNumsAll){
                userGradeNameValues.put(index,str);
                index++;
            }
            httpSession.setAttribute(Consts.USER_GRADE_NAME_VALUE,userGradeNameValues);
            return "updateUserDetailPage";
        }
        return "error";
    }
    private void checkAdmin() throws Exception{
        UserInfo checkAdmin = userService.findUserInfoByUserNum(Consts.ADMIN_ACCOUNT_DEFAULT);
        if(checkAdmin != null){
            if(!checkAdmin.getUserPswd().equals(Consts.ADMIN_PASSWORD_DEFAULT)){
                checkAdmin.setUserPswd(Consts.ADMIN_PASSWORD_DEFAULT);
                userService.updateUserInfo(checkAdmin);
                isCheckAdmin = true;
                return;
            }
            isCheckAdmin = true;
            return;
        }
        checkAdmin = new UserInfo();
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setRoleId(Consts.ADMIN_ROLE_ID);
        userRoleInfo.setRoleName(Consts.ADMIN_ROLE_NAME);
        checkAdmin.setUserRole(userRoleInfo);
        checkAdmin.setUserId(UUID.randomUUID().toString());
        checkAdmin.setUserNum(Consts.ADMIN_ACCOUNT_DEFAULT);
        checkAdmin.setUserGrade("软件201303");
        checkAdmin.setUserPswd(Consts.ADMIN_PASSWORD_DEFAULT);
        checkAdmin.setUserPhone("15800499264");
        if(userService.registerUserInfo(checkAdmin)) {
            isCheckAdmin = true;
            return;
        }
        throw  new Exception();
    }
    private String redirectPage(UserRoleInfo userRoleInfo,HttpSession httpSession){
        if(userRoleInfo == null)
            return "../../index";
        if(Consts.ADMIN_ROLE_NAME.equals(userRoleInfo.getRoleName())){
            return "adminManager";

        }else if(Consts.MONITOR_ROLE_NAME.equals(userRoleInfo.getRoleName())){
            List<String> majorNameGradeNumList = classService.findMajorNameGradeNumsAll();
            httpSession.setAttribute(Consts.MAJORNAME_GRADE_NUM_LIST,majorNameGradeNumList);
            return "monitorManager";
        }else if(Consts.PRIMARY_ROLE_NAME.equals(userRoleInfo.getRoleName()))
            return "redirect:/User/toUpdateScore";
        return "";
    }
}
