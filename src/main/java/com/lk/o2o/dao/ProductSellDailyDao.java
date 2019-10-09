package com.lk.o2o.dao;

import com.lk.o2o.entity.ProductSellDaily;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProductSellDailyDao {

    /**
     * 根据查询条件返回商品日销售的统计列表
     * @param productSellDaily 查询条件
     * @param beginTime 起始时间
     * @param endTime 结束时间
     * @return 查询结果
     */
    List<ProductSellDaily> queryProductSellDailyList(@Param("productSellDaily")ProductSellDaily productSellDaily, @Param("beginTime")Date beginTime,@Param("endTime")Date endTime);

    /**
     * 统计平台所有商品的日销售量
     * @return 结果
     */
    int insertProductSellDaily();
}
