package com.pei.handle;

import com.pei.exception.AccountException;
import com.pei.exception.AgeException;
import com.pei.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AccountException.class)
    public void doAccountException(Exception e){
        System.out.println("doAccountException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",e.getMessage());
        mv.setViewName("redirect:login.jsp");
    }

    @ExceptionHandler(value = LoginException.class)
    public ModelAndView doLoginException(Exception e){
        System.out.println("doLoginException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",e.getMessage());
        mv.setViewName("redirect:login.jsp");
        return mv;
    }

    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception e){
        System.out.println("doAgeException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",e.getMessage());
        mv.setViewName("redirect:login.jsp");
        return mv;
    }



   /* @ExceptionHandler
    public ModelAndView doOtherException(Exception e){
        System.out.println("doOtherException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips","服务器崩溃了,请稍后重置");
        mv.setViewName("/error/error");
        return mv;
    }*/
}
