package net.blf2.controller;

import net.blf2.entity.UserInfo;
import net.blf2.util.Consts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by blf2 on 17-2-20.
 */
public class PreForOpInterceptor implements HandlerInterceptor {
    private static final String path = "/WEB-INF/jsp/signin.jsp";
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession();
        UserInfo hasLoginInfo = (UserInfo)httpSession.getAttribute(Consts.LOGIN_INFO);
        if(hasLoginInfo == null){
            httpServletRequest.getRequestDispatcher(path).forward(httpServletRequest,httpServletResponse);
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
