package com.pei.controller;

import com.pei.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DicController {
    @Autowired
    private DicService dicService;
    @RequestMapping("/showDicType.do")
    public void showDicType() {
    dicService.getAllDicValueList();
    }

}
