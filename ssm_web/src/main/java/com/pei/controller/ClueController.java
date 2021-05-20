package com.pei.controller;

import com.github.pagehelper.PageInfo;

import com.pei.domain.*;
import com.pei.service.ActivityService;
import com.pei.service.ClueService;
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
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ActivityService activityService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Msg pageList(@RequestParam Map<String, Object> map) {
        PageInfo pageInfo = clueService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String[] ids) {
        Boolean flag = clueService.delete(ids);
        return flag;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public Boolean save(Clue clue, HttpServletRequest request) {
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        clue.setCreateBy(((User) request.getSession().getAttribute("user")).getCreateBy());
        System.out.println("接收到的对象 = " + clue);
        Boolean flag = clueService.save(clue);
        return flag;
    }

    @RequestMapping("/detail.do")
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView();
        Clue c = clueService.detail(id);
        mv.addObject("c", c);
        mv.setViewName("/clue/detail");
        return mv;
    }

    @RequestMapping("/getActivityListByClueId.do")
    @ResponseBody
    private List<Activity> getActivityListByClueId(String clueId) {
        System.out.println("根据id查询关联的市场活动列表");
        List<Activity> activityList = activityService.getActivityListByClueId(clueId);
        return activityList;
    }

    @RequestMapping("/getActivityListByNameAndNotByClueId.do")
    @ResponseBody
    private List<Activity> getActivityListByNameAndNotByClueId(String aname, String clueId) {
        System.out.println("进入到市场活动列表（根据市场模糊查询，排除掉已经关联指定线索的列表）");
        Map<String, Object> map = new HashMap<>();
        map.put("aname", aname);
        map.put("clueId", clueId);
        List<Activity> activityList = activityService.getActivityListByNameAndNotByClueId(map);
        return activityList;

    }

    @RequestMapping("/bund.do")
    @ResponseBody
    private boolean bund(String aid[], String cid) {
        System.out.println("执行关联市场活动的操作");
        boolean flag = clueService.bund(aid, cid);
        return flag;
    }

    @RequestMapping("/unbund.do")
    @ResponseBody
    private boolean unbund(String id) {
        System.out.println("进入到解除关联操作");
        boolean flag = clueService.unbund(id);
        return flag;
    }

    @RequestMapping("/getActivityListByName.do")
    @ResponseBody
    private List<Activity> getActivityListByName(String aname) {
        List<Activity> activityList = activityService.getActivityListByName(aname);
        return activityList;
    }

    @RequestMapping("/convert.do")
    private ModelAndView convert(String clueId, String flag, HttpServletRequest request) {
        Tran t = null;
        ModelAndView mv = new ModelAndView();
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        if ("a".equals(flag)) {
//            接受交易表单中的数据
            t = new Tran();
            String money = request.getParameter("money");
            String name = request.getParameter("name");
            String expectedDate = request.getParameter("expectedDate");
            String stage = request.getParameter("stage");
            String activityId = request.getParameter("activityId");
            String id = UUIDUtil.getUUID();
            String createTime = DateTimeUtil.getSysTime();
            t.setId(id);
            t.setCreateTime(createTime);
            t.setCreateBy(createBy);
            t.setMoney(money);
            t.setName(name);
            t.setExpectedDate(expectedDate);
            t.setStage(stage);
            t.setActivityId(activityId);
        }
        /*业务层传递的参数
         * 1.必须传递的参数clueId，有了这个后我们才知道转换哪条记录
         * 2.必须传递参数t，因为在线索转换的过程中，有可能会临时创建一笔交易（业务层的t也有可能是null）*/
        boolean flag1 = clueService.convert(clueId, t, createBy);
        if (flag1) {
            mv.setViewName("redirect:/workbench/clue/index.jsp");
        }
        mv.addObject(flag1);
        return mv;
    }

}
