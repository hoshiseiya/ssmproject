package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Contacts;
import com.pei.domain.Customer;
import com.pei.domain.CustomerRemark;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    PageInfo pageList(Map<String, Object> map);

    Customer detail(String id);

    List<CustomerRemark> getRemarkListByCid(String customerId);

    Boolean deleteRemark(String id);

    Boolean saveRemark(CustomerRemark cr);

    Boolean updateRemark(CustomerRemark cr);

    Map<String, Object> getChart();

    List<Contacts> getContactsListById(String id);
}
