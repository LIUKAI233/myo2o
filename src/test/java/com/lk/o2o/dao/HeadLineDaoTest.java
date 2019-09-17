package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testSelectHeadLineList(){
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(0);
        List<HeadLine> headLines = headLineDao.selectHeadLineList(headLine);
        for (HeadLine head: headLines) {
            System.out.println(head.getLineName());
        }
    }
}
