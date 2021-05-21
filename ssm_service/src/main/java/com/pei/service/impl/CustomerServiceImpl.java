package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ContactsDao;
import com.pei.dao.CustomerDao;
import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;
import com.pei.domain.Customer;
import com.pei.domain.CustomerRemark;
import com.pei.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ContactsDao contactsDao;

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

    @Override
    public List<CustomerRemark> getRemarkListByCid(String customerId) {
        return customerDao.getRemarkListByCid(customerId);
    }

    @Override
    public Boolean deleteRemark(String id) {
        boolean flag = true;
        int count = customerDao.deleteRemark(id);
        if (count != 1) {
            flag = false;
        }
        return flag;


    }

    @Override
    public Boolean saveRemark(CustomerRemark cr) {
        Boolean flag = true;
        int Count = customerDao.saveRemark(cr);
        if (Count != 1) {
            flag = false;
        }
        return flag;

    }

    @Override
    public Boolean updateRemark(CustomerRemark cr) {
        Boolean flag = true;
        int Count = customerDao.updateRemark(cr);
        if (Count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getChart() {
        List<Map<String, Object>> dataList = customerDao.getCharts();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", dataList);
        return map;
    }

    @Override
    public List<Contacts> getContactsListById(String id) {
        List<Contacts> contactsList = contactsDao.getContactsListById(id);
        return contactsList;
    }

}
