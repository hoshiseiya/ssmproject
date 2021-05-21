package com.pei.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ContactsService {
    PageInfo pageList(Map<String, Object> map);
}
