package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.*;
import com.pei.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Msg pageList(@RequestParam Map<String, Object> map) {
        PageInfo pageInfo = customerService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }

    @RequestMapping("/detail.do")
    public ModelAndView detail(String id) {
        ModelAndView mv = new ModelAndView();
        Customer c = customerService.detail(id);
        mv.addObject("c", c);
        mv.setViewName("/customer/detail");
        return mv;
    }

    @RequestMapping("/getRemarkListById.do")
    @ResponseBody
    public List<CustomerRemark> getRemarkListById(String customerId) {
        List<CustomerRemark> remarkList = customerService.getRemarkListByCid(customerId);
        return remarkList;
    }

    @RequestMapping("/getContactsListById.do")
    @ResponseBody
    public List<Contacts> getContactsListById(String id) {
        List<Contacts> contactsList = customerService.getContactsListById(id);
        return contactsList;
    }

    @RequestMapping("/deleteRemark.do")
    @ResponseBody
    public Boolean deleteRemark(String id) {
        Boolean flag = customerService.deleteRemark(id);
        return flag;
    }

    @RequestMapping("/saveRemark.do")
    @ResponseBody
    public Map<String, Object> saveRemark(CustomerRemark cr, HttpServletRequest request) {
        cr.setId(UUIDUtil.getUUID());
        cr.setCreateBy(((User) request.getSession().getAttribute("user")).getName());
        cr.setCreateTime(DateTimeUtil.getSysTime());
        cr.setEditFlag("0");
        Boolean flag = customerService.saveRemark(cr);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("cr", cr);
        return map;
    }

    @RequestMapping("/updateRemark.do")
    @ResponseBody
    public Map<String, Object> updateRemark(CustomerRemark cr, HttpServletRequest request) {
        cr.setEditBy(((User) request.getSession().getAttribute("user")).getName());
        cr.setEditTime(DateTimeUtil.getSysTime());
        cr.setEditFlag("1");
        Boolean flag = customerService.updateRemark(cr);
        Map<String, Object> map = new HashMap<>();
        map.put("success", flag);
        map.put("cr", cr);
        return map;
    }

    @RequestMapping("/getCharts.do")
    @ResponseBody
    public Map<String, Object> getCharts() {
        Map<String, Object> map = customerService.getChart();
        return map;
    }
}
