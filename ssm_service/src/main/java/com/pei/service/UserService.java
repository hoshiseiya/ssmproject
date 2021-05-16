package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.User;

import java.util.List;

public interface UserService {

    void save(User user);

    List<User> getUserList();


    PageInfo page(Integer pageNumber);
}
