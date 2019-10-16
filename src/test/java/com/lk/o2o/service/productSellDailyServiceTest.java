package com.lk.o2o.service;

import com.lk.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class productSellDailyServiceTest extends BaseTest {
    @Autowired
    private ProductSellDailyService productSellDailyService;

    @Test
    public void test(){
        productSellDailyService.dailyCalculate();
    }
}
