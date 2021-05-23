package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;
import com.pei.domain.CustomerRemark;

import java.util.List;
import java.util.Map;

public interface ContactsService {
    PageInfo pageList(Map<String, Object> map);

    Contacts detail(String id);

    List<ContactsRemark> getRemarkListByCid(String contactsId);

    List<Contacts> getContactsListByCid(String customerId);

    Boolean deleteRemark(String id);

    Boolean saveRemark(ContactsRemark cr);

    Boolean updateRemark(ContactsRemark cr);

}
