package com.lihao.restfulapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object>map,
                        HttpSession session){

        if(username.equals("admin") && "123456".equals(password)){
            //login successfully redirects to dashboard
            session.setAttribute("loginuser",username);
            return "redirect:/main.html";
        }else{
            //login failed, stay in login page
            map.put("msg",1);
            return  "login";
        }

    }
}
