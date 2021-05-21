package com.pei.service;

import com.github.pagehelper.PageInfo;
import com.pei.domain.*;

import java.util.List;
import java.util.Map;

public interface ClueService {
    PageInfo pageList(Map<String, Object> map);

    Boolean delete(String[] ids);

    Boolean save(Clue clue);

    Clue detail(String id);

    boolean bund(String[] aid, String cid);

    boolean unbund(String id);

    List<ClueRemark> getRemarkListByCid(String clueId);

    Boolean deleteRemarkById(String id);

    Boolean saveRemark(ClueRemark cr);

    Boolean updateRemark(ClueRemark ar);

    boolean convert(String clueId, Tran t, String createBy);
}
