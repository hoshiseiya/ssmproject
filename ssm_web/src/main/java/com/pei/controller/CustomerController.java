package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Clue;
import com.pei.domain.Customer;
import com.pei.domain.Msg;
import com.pei.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

}
