package com.lk.o2o.dao;

import com.lk.o2o.entity.Shop;

public interface ShopDao {
    /**
     * 插入商铺信息
     * @return 插入数据行数
     */
    int insertShop(Shop shop);

    /**
     * 更新商铺信息
     * @return 更改数据行数
     */
    int updateShop(Shop shop);

    /**
     * 根据店铺id查询店铺信息
     * @param shopId
     * @return
     */
    Shop queryByShopId(Long shopId);
}
