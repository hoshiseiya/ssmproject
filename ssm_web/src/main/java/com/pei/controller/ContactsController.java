package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.*;
import com.pei.service.ActivityService;
import com.pei.service.ContactsService;
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
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Msg pageList(@RequestParam Map<String, Object> map) {
        PageInfo pageInfo = contactsService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/detail.do")
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView();
        Contacts c = contactsService.detail(id);
        mv.addObject("c", c);
        mv.setViewName("/contacts/detail");
        return mv;
    }

    @RequestMapping("/getRemarkListById.do")
    @ResponseBody
    public List<ContactsRemark> getRemarkListById(String contactsId) {
        List<ContactsRemark> remarkList = contactsService.getRemarkListByCid(contactsId);
        return remarkList;
    }

    @RequestMapping("/getContactsListById.do")
    @ResponseBody
    public List<Contacts> getContactsListById(String customerId) {
        List<Contacts> contactsList = contactsService.getContactsListByCid(customerId);
        return contactsList;
    }

    @RequestMapping("/getActivityListByContactsId.do")
    @ResponseBody
    private List<Activity> getActivityListByContactsId(String contactsId) {
        List<Activity> activityList = activityService.getActivityListByContactsId(contactsId);
        return activityList;
    }


    @RequestMapping("/deleteRemark.do")
    @ResponseBody
    public Boolean deleteRemark(String id) {
        Boolean flag = contactsService.deleteRemark(id);
        return flag;
    }

    @RequestMapping("/saveRemark.do")
    @ResponseBody
    public Map<String, Object> saveRemark(ContactsRemark cr, HttpServletRequest request) {
        cr.setId(UUIDUtil.getUUID());
        cr.setCreateBy(((User) request.getSession().getAttribute("user")).getName());
        cr.setCreateTime(DateTimeUtil.getSysTime());
        cr.setEditFlag("0");
        Boolean flag = contactsService.saveRemark(cr);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("cr", cr);
        return map;
    }

    @RequestMapping("/updateRemark.do")
    @ResponseBody
    public Map<String, Object> updateRemark(ContactsRemark cr, HttpServletRequest request) {
        cr.setEditBy(((User) request.getSession().getAttribute("user")).getName());
        cr.setEditTime(DateTimeUtil.getSysTime());
        cr.setEditFlag("1");
        Boolean flag = contactsService.updateRemark(cr);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("cr", cr);
        return map;
    }
}
