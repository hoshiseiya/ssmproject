package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Activity;
import com.pei.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
  List<Activity> findAllActivity();

    PageInfo pageList(Map<String, Object> map);

    Boolean save(Activity activity);

    Boolean delete(String[] ids);

    Map<String, Object> getUserListAndActivity(String id);

    Boolean update(Activity activity);

    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    Boolean deleteRemark(String id);

    Boolean saveRemark(ActivityRemark ar);

    Boolean updateRemark(ActivityRemark ar);

    List<Activity> getActivityListByClueId(String clueId);

    List<Activity> getActivityListByContactsId(String contactsId);

    List<Activity> getActivityListByNameAndNotByClueId(Map<String, Object> map);

    List<Activity> getActivityListByName(String aname);

  Map<String, Object> getChart();
}
