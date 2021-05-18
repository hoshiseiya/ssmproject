package com.pei.dao;

import com.pei.domain.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityDao {
    List<Activity> findAll();

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    Integer save(Activity activity);

    Integer delete(String[] ids);
}
