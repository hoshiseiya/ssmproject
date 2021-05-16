package com.pei.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = LoginException.class)
    public ModelAndView doLoginException(Exception e){
        System.out.println("doLoginException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",e.getMessage());
        mv.setViewName("error");
        return mv;
    }


    @ExceptionHandler(value = AgeException.class)
    public ModelAndView doAgeException(Exception e){
        System.out.println("doAgeException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips",e.getMessage());
        mv.setViewName("error");
        return mv;
    }

    @ExceptionHandler
    public ModelAndView doOtherException(Exception e){
        System.out.println("doOtherException = " + e);

        ModelAndView mv = new ModelAndView();
        mv.addObject("tips","服务器崩溃了,请稍后重试");
        mv.setViewName("error");
        return mv;
    }
}
