package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.UserMapper;
import com.pei.domain.User;
import com.pei.domain.UserExample;
import com.pei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService") //此处名称可以定义也可以不定义
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public PageInfo page(Integer pageNumber) {
        PageHelper.startPage(pageNumber , 4);
        List<User> list = userMapper.selectByExample(new UserExample());
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
