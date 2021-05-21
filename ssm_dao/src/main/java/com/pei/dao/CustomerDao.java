package com.pei.dao;


import com.pei.domain.Clue;
import com.pei.domain.Customer;
import com.pei.domain.CustomerRemark;

import java.util.List;
import java.util.Map;

public interface CustomerDao {

    List<Customer> selectByCondition(Map<String, Object> map);

    Customer getCustomerByName(String company);

    Integer save(Customer cus);

    List<String> getCustomerName(String name);

    int saveRemark(CustomerRemark customerRemark);

    Customer detail(String id);

    List<Map<String, Object>> getCharts();
}
