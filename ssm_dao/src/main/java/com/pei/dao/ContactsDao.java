package com.pei.dao;

import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;
import java.util.List;
import java.util.Map;

public interface ContactsDao {
    int save(Contacts con);


    List<Contacts> selectByCondition(Map<String, Object> map);

    Contacts detail(String id);

    List<ContactsRemark> getRemarkListByCid(String contactsId);

    Integer deleteRemark(String id);

    Integer saveRemark(ContactsRemark cr);

    Integer updateRemark(ContactsRemark cr);

    List<Contacts> getContactsListById(String id);
}
