package com.pei.dao;


import com.pei.domain.Customer;
import com.pei.domain.CustomerRemark;

import java.util.List;

public interface CustomerDao {

    Customer getCustomerByName(String company);

    Integer save(Customer cus);

    List<String> getCustomerName(String name);

    int saveRemark(CustomerRemark customerRemark);
}
