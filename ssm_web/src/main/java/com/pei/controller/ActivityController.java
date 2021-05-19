package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Activity;
import com.pei.domain.ActivityRemark;
import com.pei.domain.Msg;
import com.pei.domain.User;
import com.pei.service.ActivityService;
import com.pei.service.UserService;
import com.pei.utils.DateTimeUtil;
import com.pei.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public Msg pageList(@RequestParam Map<String, Object> map) {
        PageInfo pageInfo = activityService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public Boolean save(Activity activity, HttpServletRequest request) {
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setCreateBy(((User) request.getSession().getAttribute("user")).getCreateBy());
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

    @RequestMapping("/getUserListAndActivity.do")
    @ResponseBody
    public Map<String, Object> getUserListAndActivity(String id) {
        Map<String, Object> map = activityService.getUserListAndActivity(id);
        return map;
    }

    @RequestMapping("/update.do")
    @ResponseBody
    public Boolean update(Activity activity, HttpServletRequest request) {
        activity.setEditBy(((User) request.getSession().getAttribute("user")).getName());
        activity.setEditTime(DateTimeUtil.getSysTime());
        Boolean flag = activityService.update(activity);
        return flag;
    }

    @RequestMapping("/detail.do")
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView();
        Activity a = activityService.detail(id);
        mv.addObject("a", a);
        mv.setViewName("/activity/detail");
        return mv;
    }

    @RequestMapping("/getRemarkListById.do")
    @ResponseBody
    public List<ActivityRemark> getRemarkListById(String activityId) {
        List<ActivityRemark> remarkList = activityService.getRemarkListByAid(activityId);
        return remarkList;
    }

    @RequestMapping("/deleteRemark.do")
    @ResponseBody
    public Boolean deleteRemark(String id) {
        Boolean flag = activityService.deleteRemark(id);
        return flag;
    }

    @RequestMapping("/saveRemark.do")
    @ResponseBody
    public Map<String, Object> saveRemark(ActivityRemark ar, HttpServletRequest request) {
        System.out.println("ar = " + ar);
        ar.setId(UUIDUtil.getUUID());
        ar.setCreateBy(((User) request.getSession().getAttribute("user")).getCreateBy());
        ar.setCreateTime(DateTimeUtil.getSysTime());
        ar.setEditFlag("0");
        Boolean flag = activityService.saveRemark(ar);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("ar", ar);
        return map;
    }

    @RequestMapping("/updateRemark.do")
    @ResponseBody
    public Map<String, Object> updateRemark(ActivityRemark ar, HttpServletRequest request) {
        ar.setEditBy(((User) request.getSession().getAttribute("user")).getCreateBy());
        ar.setEditTime(DateTimeUtil.getSysTime());
        ar.setEditFlag("1");
        Boolean flag = activityService.updateRemark(ar);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("ar", ar);
        return map;
    }
}
