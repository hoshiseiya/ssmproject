package com.pei.service;

import com.pei.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List<DicValue>> getAllDicValueList();
}
