package com.lihao.restfulapi.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginuser");
        if(user == null){
            //log in failure, forward request back to /index.html
            request.setAttribute("msg","Not authorized, please login first");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //logged in, give authority
            return true;
        }

    }
}
