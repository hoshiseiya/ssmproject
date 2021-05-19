package com.pei.controller;

import com.pei.domain.User;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    private List<User> getUserList() {
        List<User> userList = userService.getUserList();
        return userList;
    }

}
