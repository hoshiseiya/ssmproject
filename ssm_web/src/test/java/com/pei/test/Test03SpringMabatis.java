package com.pei.test;

import com.pei.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
   /* @Test
    public void testSave() {
        Account account = new Account();
        account.setName(" 测 试 账 号 ");
        account.setMoney(1234f);
        accountService.saveAccount(account);
    }*/
}

