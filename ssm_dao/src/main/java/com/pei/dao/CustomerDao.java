package com.pei.dao;

import com.pei.domain.Customer;
import com.pei.domain.CustomerRemark;

import java.util.List;
import java.util.Map;

public interface CustomerDao {

    List<Customer> selectByCondition(Map<String, Object> map);

    Customer getCustomerByName(String company);

    Integer save(Customer cus);

    List<String> getCustomerName(String name);

    List<CustomerRemark> getRemarkListByCid(String customerId);

    Integer deleteRemark(String id);

    Integer saveRemark(CustomerRemark cr);

    Integer updateRemark(CustomerRemark cr);

    Customer detail(String id);

    List<Map<String, Object>> getCharts();
}
