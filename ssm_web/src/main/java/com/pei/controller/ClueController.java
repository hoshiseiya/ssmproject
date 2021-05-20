package com.pei.controller;

import com.github.pagehelper.PageInfo;

import com.pei.domain.Msg;
import com.pei.domain.User;
import com.pei.service.ClueService;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    private UserService userService;
    @Autowired
    private ClueService clueService;

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


}
