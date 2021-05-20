package com.pei.dao;
import com.pei.domain.Clue;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ClueDao {

    List<Clue> selectByCondition(Map<String, Object> map);
}