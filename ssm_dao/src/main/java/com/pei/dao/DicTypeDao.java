package com.pei.dao;


import com.pei.domain.DicType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DicTypeDao {
    List<DicType> getAllTypeList();
}
