package com.pei.dao;

import com.pei.domain.Activity;
import com.pei.domain.ActivityRemark;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityDao {
    List<Activity> findAll();

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    Integer save(Activity activity);

    Integer delete(String[] ids);

    Activity getActivityById(String id);

    Integer update(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    Integer deleteRemark(String id);

    Integer saveRemark(ActivityRemark ar);

    Integer updateRemark(ActivityRemark ar);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityListByNameAndNotByClueId(Map<String, Object> map);

    List<Activity> getActivityListByName(String aname);

    List<Activity> getChart();
}
