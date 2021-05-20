package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueService {
    PageInfo pageList(Map<String, Object> map);

    Boolean delete(String[] ids);

//    public PageInfo<Clue> getClueList(String pageNum);
}
