package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ContactsDao;
import com.pei.domain.Contacts;
import com.pei.domain.Customer;
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
}
