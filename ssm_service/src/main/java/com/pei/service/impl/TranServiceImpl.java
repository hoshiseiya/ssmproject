package com.pei.service.impl;

import com.pei.dao.TranDao;
import com.pei.domain.Tran;
import com.pei.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranServiceImpl implements TranService {
    @Autowired
    private TranDao tranDao;

    @Override
    public List<Tran> findAllTrans() {
        List<Tran> list = tranDao.findAllTrans();
        return list;
    }
}
