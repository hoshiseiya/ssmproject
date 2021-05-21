package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Msg;
import com.pei.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/pageList.do")
    @ResponseBody
    public Msg pageList(@RequestParam Map<String, Object> map) {
        PageInfo pageInfo = contactsService.pageList(map);
        return Msg.success().add("pageInfo", pageInfo);
    }
}
