package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.Response;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AccessInterceptor
 * @Description TODO
 * @Author xizhonghuai
 * @Date 2019-06-18 13:04
 * @Version 1.0
 */
public class AccessInterceptor extends HandlerInterceptorAdapter {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String url = request.getRequestURL().toString();



        if (url.contains("data.api")){



            return true;
        }

        if (url.contains("login.html")){
            return true;
        }

        if (url.contains("doLogin")){
            return true;
        }

        String token = (String) request.getSession().getAttribute("token");

        if (token == null){

            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();



            writer.write(new Response(401 ,"未登陆用户").toString());


            return false;
        }




        return true;
    }
}
