package com.pei.dao;
import com.pei.domain.Activity;
import com.pei.domain.ActivityRemark;
import com.pei.domain.Clue;
import com.pei.domain.ClueRemark;

import java.util.List;
import java.util.Map;

public interface ClueDao {

    List<Clue> selectByCondition(Map<String, Object> map);

    Integer delete(String[] ids);

    Integer save(Clue clue);

    Clue detail(String id);

    Clue getById(String clueId);

    List<ClueRemark> getListByClueId(String clueId);

    Integer deleteRemark(ClueRemark clueRemark);

    List<ClueRemark> getRemarkListByCid(String clueId);

    Integer deleteRemarkById(String id);

    Integer saveRemark(ClueRemark cr);

    Integer updateRemark(ClueRemark cr);
}