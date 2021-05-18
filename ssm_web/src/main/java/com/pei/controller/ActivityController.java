package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Activity;
import com.pei.domain.Msg;
import com.pei.domain.User;
import com.pei.service.ActivityService;
import com.pei.service.UserService;
import com.pei.utils.DateTimeUtil;
import com.pei.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public Msg pageList(@RequestParam Map<String,Object> map) {
        PageInfo pageInfo = activityService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public Boolean save(Activity activity, HttpServletRequest request) {
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setCreateBy(((User)request.getSession().getAttribute("user")).getCreateBy());
        System.out.println("接收到的对象 = " + activity);
        Boolean flag = activityService.save(activity);
        System.out.println("flag = " + flag);
        return flag;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String[] ids) {
        Boolean flag = activityService.delete(ids);
        return flag;
    }
}
