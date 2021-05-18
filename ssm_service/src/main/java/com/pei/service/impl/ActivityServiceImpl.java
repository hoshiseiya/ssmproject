package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ActivityDao;
import com.pei.dao.UserDao;
import com.pei.domain.Activity;
import com.pei.domain.User;
import com.pei.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Activity> findAllActivity() {
        return activityDao.findAll();
    }

    @Override
    public PageInfo pageList(Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt((String) map.get("pageNum")), 4);//查第pageNum页，每页显示4条记录
        PageHelper.orderBy("startDate desc");
        List<Activity> activityList = activityDao.getActivityListByCondition(map);
        PageInfo<Activity> pageInfo = new PageInfo<>(activityList);
        return pageInfo;
    }

    @Override
    public Boolean save(Activity activity) {
        boolean flag = true;
        int count = activityDao.save(activity);
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Boolean delete(String[] ids) {
        boolean flag = true;


        //删除市场活动
        int Count3 = activityDao.delete(ids);
        if (Count3 != ids.length) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        List<User> userList = userDao.getUserList();
        Activity a = activityDao.getActivityById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("userList",userList);
        map.put("a",a);
        return map;
    }

    @Override
    public Boolean update(Activity activity) {
        boolean flag = true;
        int count = activityDao.update(activity);
        if (count != 1) {
            flag = false;
        }
        return flag;

    }
}
