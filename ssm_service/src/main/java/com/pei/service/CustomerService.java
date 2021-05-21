package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Customer;

import java.util.Map;

public interface CustomerService {
    PageInfo pageList(Map<String, Object> map);

    Customer detail(String id);
}
