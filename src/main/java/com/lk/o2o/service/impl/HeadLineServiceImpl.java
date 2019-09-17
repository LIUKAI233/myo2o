package com.lk.o2o.service.impl;

import com.lk.o2o.dao.HeadLineDao;
import com.lk.o2o.entity.HeadLine;
import com.lk.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    //查询符合条件的头条集合
    public List<HeadLine> queryHeadLineList(HeadLine headLineCondition) {
        return headLineDao.selectHeadLineList(headLineCondition);
    }
}
