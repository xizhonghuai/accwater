package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName PageController
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-07-17 15:10
 * @Version 1.0
 */

@Controller
public class PageController {

    @RequestMapping("/")
    public void home(HttpServletResponse response)throws Exception{
        response.sendRedirect("html/login.html");

    }


    @RequestMapping("/getToken")
    @ResponseBody
    public String getToken(HttpServletRequest request) {

        return  (String) request.getSession().getAttribute("token");
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Boolean doLogin(HttpServletRequest request, @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "password", required = false) String password){

        String admin_p = "cqdb305086";
        String guest_p = "guest0001";

        String token = null;

        if ("admin".equals(name) && admin_p.equals(password)){

            request.getSession().setAttribute("token", "admin");

            return true;

        }

        if ("guest".equals(name) && guest_p.equals(password)){
            request.getSession().setAttribute("token", "guest");

            return true;
        }

        return false;
    }




}
