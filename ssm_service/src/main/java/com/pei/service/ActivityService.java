package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    List<Activity> findAllActivity();

    PageInfo pageList(Map<String, Object> map);
}
