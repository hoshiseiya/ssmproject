package com.pei.listener;

import com.pei.domain.DicValue;
import com.pei.service.DicService;
import com.pei.service.impl.DicServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        System.out.println("全局作用域对象application已经创建");
        System.out.println("准备获取数据字典");
        //获取全局作用域对象
        ServletContext application = event.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
        DicService dicService = (DicService) applicationContext.getBean("dicServiceImpl");
        //DicService dicService = applicationContext.getBean(DicServiceImpl.class);

        Map<String, List<DicValue>> map = dicService.getAllDicValueList();
        Set<String> set = map.keySet();
        for (String key : set) {
            application.setAttribute(key, map.get(key));
        }
        System.out.println("全局作用域中已经存入数据字典");

        //解析properties文件
        ResourceBundle rb = ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> e = rb.getKeys();

        Map<String, String> pMap = new HashMap<>();

        while (e.hasMoreElements()) {
            //阶段
            String key = e.nextElement();
            //可能性
            String value = rb.getString(key);

            pMap.put(key, value);

        }
        application.setAttribute("pMap", pMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
