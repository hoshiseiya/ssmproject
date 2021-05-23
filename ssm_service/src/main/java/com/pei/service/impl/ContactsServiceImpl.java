package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ContactsDao;
import com.pei.domain.Contacts;
import com.pei.domain.ContactsRemark;
import com.pei.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContactsServiceImpl implements ContactsService {
@Autowired
private ContactsDao contactsDao;
    @Override
    public PageInfo pageList(Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt((String) map.get("pageNum")), 4);
        PageHelper.orderBy("createTime desc");
        List<Contacts> contactsList = contactsDao.selectByCondition(map);
        PageInfo<Contacts> pageInfo = new PageInfo<>(contactsList);
        return pageInfo;
    }

    @Override
    public Contacts detail(String id) {
        Contacts c = contactsDao.detail(id);
        return c;
    }

    @Override
    public List<ContactsRemark> getRemarkListByCid(String contactsId) {
        return contactsDao.getRemarkListByCid(contactsId);
    }

    @Override
    public List<Contacts> getContactsListByCid(String customerId) {
        return contactsDao.getContactsListById(customerId);
    }

    @Override
    public Boolean deleteRemark(String id) {
        boolean flag = true;
        int count = contactsDao.deleteRemark(id);
        if (count != 1) {
            flag = false;
        }
        return flag;


    }

    @Override
    public Boolean saveRemark(ContactsRemark cr) {
        Boolean flag = true;
        int Count = contactsDao.saveRemark(cr);
        if (Count != 1) {
            flag = false;
        }
        return flag;

    }

    @Override
    public Boolean updateRemark(ContactsRemark cr) {
        Boolean flag = true;
        int Count = contactsDao.updateRemark(cr);
        if (Count != 1) {
            flag = false;
        }
        return flag;
    }
}
