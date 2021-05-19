package com.pei.listener;

import com.pei.domain.DicValue;
import com.pei.service.DicService;
import com.pei.service.impl.DicServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {


        System.out.println("全局作用域对象application已经创建");
        System.out.println("准备获取数据字典");
        //获取全局作用域对象
        ServletContext application = event.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
        DicService dicService = (DicService) applicationContext.getBean("dicServiceImpl");
//        DicService dicService = applicationContext.getBean(DicServiceImpl.class);
        
        Map<String, List<DicValue>> map = dicService.getAllDicValueList();
        Set<String> set = map.keySet();
        for (String key : set) {
            application.setAttribute(key, map.get(key));
        }
        System.out.println("全局作用域中已经存入数据字典");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
