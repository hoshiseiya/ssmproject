package com.pei.service.impl;

import com.pei.dao.UserDao;
import com.pei.domain.User;
import com.pei.exception.AccountException;
import com.pei.exception.LoginException;
import com.pei.exception.MyUserException;
import com.pei.service.UserService;
import com.pei.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws MyUserException{
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        User user = userDao.login(map);

        if (user == null) {
            throw new AccountException("账号密码错误!");
        }
//        如果程序往下执行，说明账号密码正确，需要继续判断其他三项信息
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime) < 0) {
            throw new AccountException("账号已失效!");
        }
//        锁定状态判断
        if ("0".equals(user.getLockState())) {
            throw new AccountException("账号已锁定!");
        }
//        判断ip地址是否符合 允许ip是否包含登录ip
        if(!user.getAllowIps().contains(ip)){
            throw new LoginException("IP地址受限");
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;
    }
}
