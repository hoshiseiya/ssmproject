package com.pei.dao;

import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;

public interface ContactsDao {
    int save(Contacts con);
    int saveRemark(ContactsRemark contactsRemark);
}
