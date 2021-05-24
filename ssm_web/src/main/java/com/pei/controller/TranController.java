package com.pei.controller;

import com.pei.domain.Tran;
import com.pei.domain.User;
import com.pei.service.CustomerService;
import com.pei.service.TranService;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/tran")
public class TranController {
    @Autowired
    private TranService tranService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/add.do")
    public ModelAndView add() {
        ModelAndView mv = new ModelAndView();
        List<User> uList = userService.getUserList();
        mv.addObject("uList", uList);
        mv.setViewName("/transaction/save");
        return mv;
    }

    @RequestMapping("/getCustomerName.do")
    @ResponseBody
    private List<String> getCustomerName(String name) {
        List<String> sList = customerService.getCustomerName(name);
        return sList;

    }

    @RequestMapping("/getAllTrans.do")
    public ModelAndView getAllTrans() {
        List<Tran> list = tranService.findAllTrans();
        ModelAndView mv = new ModelAndView();
        mv.addObject("transList", list);
        mv.setViewName("transaction/index");
        return mv;
    }
}
