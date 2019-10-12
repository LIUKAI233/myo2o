package com.lk.o2o.service;

public interface ProductSellDailyService {

    /**
     * 每日定时对所有商铺的商品销量进行统计
     */
    void dailyCalculate();
}
