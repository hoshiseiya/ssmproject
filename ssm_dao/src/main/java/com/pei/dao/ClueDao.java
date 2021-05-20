package com.pei.dao;
import com.pei.domain.Activity;
import com.pei.domain.Clue;
import java.util.List;
import java.util.Map;

public interface ClueDao {

    List<Clue> selectByCondition(Map<String, Object> map);

    Integer delete(String[] ids);

    Integer save(Clue clue);

    Clue detail(String id);
}