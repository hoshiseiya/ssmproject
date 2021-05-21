package com.pei.dao;

import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;

import java.util.List;
import java.util.Map;

public interface ContactsDao {
    int save(Contacts con);
    int saveRemark(ContactsRemark contactsRemark);
    List<Contacts> selectByCondition(Map<String, Object> map);
}
