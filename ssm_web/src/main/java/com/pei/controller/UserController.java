package com.pei.controller;

import com.github.pagehelper.PageInfo;
import com.pei.domain.User;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/saveUser")
    public String saveAccount(User user)
    {
        userService.save(user);
        return "redirect";
    }
    /**
     * 查询所有账户
     * @return
     */
    @RequestMapping("/findAllUser")
    public ModelAndView findAllUser() {
        List<User> userList = userService.getUserList();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", userList);
        mv.setViewName("success");
        return mv;
    }

    @RequestMapping("page")
    public String page(int pageNum, Model model, HttpServletRequest request){
        PageInfo pageInfo = userService.page(pageNum);
        //System.out.println("pageInfo = " + pageInfo);
        model.addAttribute("url" , request.getContextPath()+"/user/page?r="+ Math.random());
        model.addAttribute("page" , pageInfo);
        return "pageUser";
    }
}
