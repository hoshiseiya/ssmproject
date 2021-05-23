package com.pei.controller;


import com.pei.domain.User;
import com.pei.exception.LoginException;
import com.pei.exception.MyUserException;
import com.pei.service.UserService;
import com.pei.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, Object> login(String loginAct, String loginPwd, HttpServletRequest request) {
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.login(loginAct, loginPwd, ip);
            request.getSession().setAttribute("user", user);
            map.put("success", true);
        } catch (Exception e) {
            //            一旦执行catch块 表示抛出异常 登录失败
            e.printStackTrace();
            String msg = e.getMessage();
            map.put("success", false);
            map.put("msg", msg);
        }
        return map;
    }

}
