package com.lk.o2o.service;

import com.lk.o2o.entity.ProductSellDaily;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyService {

    /**
     * 每日定时对所有商铺的商品销量进行统计
     */
    void dailyCalculate();

    /**
     * 查询在时间段内所有符合条件的集合
     * @param productSellDaily 查询条件
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 查询到的集合
     */
    List<ProductSellDaily> queryProductSellDailyList(ProductSellDaily productSellDaily, Date beginTime,Date endTime);
}
