package net.blf2.controller;

import javafx.beans.binding.ObjectExpression;
import net.blf2.entity.ItemsInfo;
import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import net.blf2.service.IClassService;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String registerMonitorInfo(HttpSession httpSession,String userNum,String userPswd,String userPhone,String userNote,String invitationCode){
        try {
            if(!Tools.checkInvitationCode(invitationCode,userNum)){
                httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,"验证码错误，请重新输入，如果多次出现，请联系管理员！");
                return "error";
            }
        }catch (Exception ex){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE, "验证出错，请联系管理员！");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(UUID.randomUUID().toString());
        userInfo.setUserNum(userNum);
        userInfo.setUserPswd(userNum);
        userInfo.setUserNote(userNote);
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setRoleId(Consts.MONITOR_ROLE_ID);
        userRoleInfo.setRoleName(Consts.MONITOR_ROLE_NAME);
        userInfo.setUserRole(userRoleInfo);
        userService.registerUserInfo(userInfo);
        return this.redirectPage(userRoleInfo);
    }
    @RequestMapping(value = "/submitInfo",method = RequestMethod.POST)
    public String submitInfo(UserInfo userInfo,List<ItemsInfo> itemsInfoList,HttpSession httpSession) throws Exception{
        UserInfo findUserInfo = null;
        try {
            findUserInfo = userService.findUserInfoByUserNum(userInfo.getUserNum());
        }catch (Exception ex){
            httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE,ex.getMessage());
            throw new Exception();
        }
        if(findUserInfo == null){
            UserRoleInfo userRoleInfo = new UserRoleInfo();
            userRoleInfo.setRoleId(Consts.PRIMARY_ROLR_ID);
            userRoleInfo.setRoleName(Consts.ADMIN_ROLE_NAME);
            userInfo.setUserRole(userRoleInfo);
            userService.registerUserInfo(userInfo);
            findUserInfo = userService.findUserInfoByUserNum(userInfo.getUserNum());
        }
        Map<String,Object> itemNameValueMap = new HashMap<String, Object>();
        double sum = 0.0;
        for(ItemsInfo itemsInfo : itemsInfoList){
            itemNameValueMap.put(itemsInfo.getItemName(),itemsInfo.getItemValue());
            sum += itemsInfo.getItemValue();
        }
        itemNameValueMap.put(Consts.USER_SUM_SCORE, sum);
        itemNameValueMap.put(Consts.MONGO_PRIMARY_KEY_NAME, findUserInfo.getUserId());
        return this.redirectPage(userInfo.getUserRole());
    }
    @RequestMapping(value = "/updateScore",method = RequestMethod.POST)
    public String updateScore(HttpSession httpSession,UserInfo userInfo,List<ItemsInfo> itemsInfoList){
        if(itemsInfoList != null && userInfo != null){
            Map<String,Object>scoreDetailMap = new HashMap<String, Object>();
            scoreDetailMap.put(Consts.MONGO_PRIMARY_KEY_NAME, userInfo.getUserId());
            Double sum = 0.0;
            for (ItemsInfo itemsInfo : itemsInfoList){
                scoreDetailMap.put(itemsInfo.getItemName(),itemsInfo.getItemValue());
                sum += itemsInfo.getItemValue();
            }
            scoreDetailMap.put(Consts.USER_SUM_SCORE, sum);
            return "findUserInfoPage";
        }
        httpSession.setAttribute(Consts.WEB_ERROR_MWSSAGE, "未知错误!!!");
        return "error";
    }
    @RequestMapping(value = "findUserInfoAndScore",method = RequestMethod.POST)
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
                        @RequestParam(value = "userPswd",required = true)String userPswd) throws Exception{
        UserInfo userInfo = null;
        if((userInfo = userService.checkLoginInfo(userNum,userPswd)) != null){
            return redirectPage(userInfo.getUserRole());
        }
        return redirectPage(null);
    }
    private String redirectPage(UserRoleInfo userRoleInfo){
        if(userRoleInfo == null)
            return "index";
        if(Consts.ADMIN_ROLE_NAME.equals(userRoleInfo.getRoleName())){
            return "";
        }else if(Consts.MONITOR_ROLE_ID.equals(userRoleInfo.getRoleName())){
            return "";
        }
        return "";
    }
}
