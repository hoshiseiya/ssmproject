package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ActivityDao;
import com.pei.domain.Activity;
import com.pei.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Activity> findAllActivity() {
        return activityDao.findAll();
    }

    @Override
    public PageInfo pageList(Map<String, Object> map) {
        PageHelper.startPage((Integer)map.get("pageNum"),4);//查第pageNum页，每页显示4条记录
        List<Activity> activityList = activityDao.getActivityListByCondition(map);
        PageInfo<Activity> pageInfo = new PageInfo<>(activityList);
        return pageInfo;
    }
}
