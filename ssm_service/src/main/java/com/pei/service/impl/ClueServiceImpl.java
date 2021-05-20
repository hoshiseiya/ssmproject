package com.pei.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pei.dao.ClueDao;
import com.pei.dao.UserDao;
import com.pei.domain.Clue;
import com.pei.domain.User;
import com.pei.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueDao clueDao;

    @Override
    public PageInfo pageList(Map<String, Object> map) {
        PageHelper.startPage(Integer.parseInt((String) map.get("pageNum")), 4);
        PageHelper.orderBy("createTime desc");
        List<Clue> clueList = clueDao.selectByCondition(map);
        PageInfo<Clue> pageInfo = new PageInfo<>(clueList);
        return pageInfo;
    }

    @Override
    public Boolean delete(String[] ids) {
        boolean flag = true;


        //删除市场活动
        int Count3 = clueDao.delete(ids);
        if (Count3 != ids.length) {
            flag = false;
        }
        return flag;
    }
}
