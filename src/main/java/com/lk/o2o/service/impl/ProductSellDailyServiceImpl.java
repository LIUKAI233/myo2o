package com.lk.o2o.service.impl;

import com.lk.o2o.service.ProductSellDailyService;
import org.springframework.stereotype.Service;

@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {
    @Override
    public void dailyCalculate() {
        System.out.println("Quartz跑起来了");
    }
}
