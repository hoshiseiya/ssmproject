package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Msg;
import com.pei.domain.User;
import com.pei.service.ActivityService;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    @ResponseBody
    public List<User> findAll() {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Msg pageList(Integer pageNum, String name, String owner, String startDate, String endDate) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageNum",pageNum);
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        PageInfo pageInfo = activityService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }
}
