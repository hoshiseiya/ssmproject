package com.pei.dao;
import com.pei.domain.DicValue;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}
