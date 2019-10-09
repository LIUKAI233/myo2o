package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductSellDailyDaoTest extends BaseTest {
    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Test
    public void testqueryProductSellDailyList(){
        productSellDailyDao.queryProductSellDailyList(null,null,null);
    }

    @Test
    public void testinsertProductSellDaily(){
        productSellDailyDao.insertProductSellDaily();
    }
}
