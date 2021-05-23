package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ActivityDao;
import com.pei.dao.UserDao;
import com.pei.domain.Activity;
import com.pei.domain.ActivityRemark;
import com.pei.domain.User;
import com.pei.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        PageHelper.orderBy("createTime desc");
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
        map.put("userList", userList);
        map.put("a", a);
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

    @Override
    public Activity detail(String id) {
        Activity a = activityDao.detail(id);
        return a;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String activityId) {
        return activityDao.getRemarkListByAid(activityId);
    }

    @Override
    public Boolean deleteRemark(String id) {
        boolean flag = true;
        int count = activityDao.deleteRemark(id);
        if (count != 1) {
            flag = false;
        }
        return flag;


    }

    @Override
    public Boolean saveRemark(ActivityRemark ar) {
        Boolean flag = true;
        int Count = activityDao.saveRemark(ar);
        if (Count != 1) {
            flag = false;
        }
        return flag;

    }

    @Override
    public Boolean updateRemark(ActivityRemark ar) {
        Boolean flag = true;
        int Count = activityDao.updateRemark(ar);
        if (Count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {
        List<Activity> activityList = activityDao.getActivityListByClueId(clueId);
        return activityList;
    }

    @Override
    public List<Activity> getActivityListByContactsId(String contactsId) {
        List<Activity> activityList = activityDao.getActivityListByContactsId(contactsId);
        return activityList;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, Object> map) {
        List<Activity> activityList = activityDao.getActivityListByNameAndNotByClueId(map);
        return activityList;
    }

    @Override
    public List<Activity> getActivityListByName(String aname) {
        List<Activity> activityList = activityDao.getActivityListByName(aname);
        return activityList;
    }

    @Override
    public Map<String, Object> getChart() {
        List<Activity> list = activityDao.getChart();
        List<String> nameList = new ArrayList<>();
        List<Integer> costList = new ArrayList<>();
        for (Activity activity : list) {
            String name = activity.getName();
            String cost = activity.getCost();
            nameList.add(name);
            costList.add(Integer.parseInt(cost));
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("nameList",nameList);
        map.put("costList",costList);
        return map;
    }
}
