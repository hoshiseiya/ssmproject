package com.pei.dao;


import com.pei.domain.Tran;

public interface TranDao {

    int save(Tran t);

    Tran detail(String id);

    int changeStage(Tran t);
}
