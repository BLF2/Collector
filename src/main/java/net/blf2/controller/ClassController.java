package net.blf2.controller;

import net.blf2.entity.ClassInfo;
import net.blf2.entity.UserInfo;
import net.blf2.service.IClassService;
import net.blf2.service.impl.ClassService;
import net.blf2.util.Consts;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

/**
 * Created by blf2 on 17-2-16.
 */
@RequestMapping("/Class")
public class ClassController {
    @Resource
    private IClassService classService;

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    @RequestMapping("/createClass")
    public String createClassInfo(ClassInfo classInfo,HttpSession httpSession){
        UserInfo userInfo = (UserInfo)httpSession.getAttribute(Consts.LOGIN_INFO);
        classInfo.setMonitorInfo(userInfo);
        classService.registerClassInfo(classInfo);
        return "";
    }
}
