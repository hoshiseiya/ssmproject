package com.pei.dao;


import com.pei.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    User login(Map<String, Object> map);

    List<User> getUserList();
}