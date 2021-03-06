package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.User;
import com.pei.exception.MyUserException;

import javax.security.auth.login.LoginException;
import java.util.List;

public interface UserService {

    User login(String loginAct, String loginPwd,String ip)throws MyUserException;

    List<User> getUserList();

}
