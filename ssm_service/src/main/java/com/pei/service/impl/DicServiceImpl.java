package com.pei.service.impl;

import com.pei.dao.DicTypeDao;
import com.pei.dao.DicValueDao;
import com.pei.domain.DicType;
import com.pei.domain.DicValue;
import com.pei.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DicServiceImpl implements DicService {
    @Autowired
    private DicTypeDao dicTypeDao;
    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public Map<String, List<DicValue>> getAllDicValueList() {
        Map<String, List<DicValue>> map = new HashMap<>();

        //获取所有的字典类型
        List<DicType> dtList = dicTypeDao.getAllTypeList();

        //根据类型查出对应类型的所有value封装到集合中
        for (DicType dt : dtList) {
            String code = dt.getCode();
            List<DicValue> dvList = dicValueDao.getListByCode(code);
            map.put(code+"List",dvList);
        }
        return map;
    }
}
