package com.pei.dao;


import com.pei.domain.Tran;

import java.util.List;

public interface TranDao {

    int save(Tran t);

    Tran detail(String id);

    int changeStage(Tran t);

    List<Tran> findAllTrans();
}
