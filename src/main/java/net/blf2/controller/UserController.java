package net.blf2.controller;

import net.blf2.entity.UserInfo;
import net.blf2.entity.UserRoleInfo;
import net.blf2.service.IUserService;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.awt.windows.ThemeReader;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by blf2 on 17-2-13.
 */
@Controller("UserController")
@RequestMapping("/User")
public class UserController {
    @Resource
    private IUserService userService;

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
    @RequestMapping(value = )
    private String redirectPage(UserRoleInfo userRoleInfo){
        if(Consts.ADMIN_ROLE_NAME.equals(userRoleInfo.getRoleName())){
            return "";
        }else if(Consts.MONITOR_ROLE_ID.equals(userRoleInfo.getRoleName())){
            return "";
        }
        return "";
    }
}
