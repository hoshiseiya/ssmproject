package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.CustomerDao;

import com.pei.domain.Clue;
import com.pei.domain.Customer;
import com.pei.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CustomerServiceImpl implements CustomerService {
@Autowired
private CustomerDao customerDao;
    @Override
    public PageInfo pageList(Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt((String) map.get("pageNum")), 4);
        PageHelper.orderBy("createTime desc");
        List<Customer> customerList = customerDao.selectByCondition(map);
        PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
        return pageInfo;
    }

    @Override
    public Customer detail(String id) {
        Customer c = customerDao.detail(id);
        return c;
    }
}
