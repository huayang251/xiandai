package com.kuang.interceptor;

import com.kuang.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @auther shkstart
 * @date 2021/5/23 - 22:09
 */
public class LoginInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();

        if(url.indexOf("/login.action")>=0){
            return true;
        }

        if(url.indexOf("/register.action")>=0){
            return true;
        }

        if(url.indexOf("/toregister")>=0){
            return true;
        }
        if(url.indexOf("/verification")>=0){
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("USER_SESSION");

        if(user!=null){
            return true;
        }

        request.setAttribute("msg","您还没有登录，请先登录！");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);

        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
