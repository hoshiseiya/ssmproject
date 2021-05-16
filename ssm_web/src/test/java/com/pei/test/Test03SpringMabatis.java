package com.pei.test;

import com.pei.domain.User;
import com.pei.service.UserService;
import com.pei.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * 测试 spring 整合 mybatis
 * @Company
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring.xml"})
public class Test03SpringMabatis {
    @Autowired
    private UserService userService;
    @Test
    public void testFindAll() {
        List list = userService.getUserList();
        System.out.println(list);
    }

    @Test
    public void testLogin() throws LoginException {
        String loginAct = "zs";
        String loginPwd = "123";
        loginPwd = MD5Util.getMD5(loginPwd);
        userService.login(loginAct,loginPwd,"12321313");
    }
}

