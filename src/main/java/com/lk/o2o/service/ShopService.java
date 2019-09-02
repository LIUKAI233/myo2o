package com.lk.o2o.service;

import com.lk.o2o.dto.ShopExecution;
import com.lk.o2o.entity.Shop;

import java.io.InputStream;

public interface ShopService {

    /**
     * 根据传入的店铺id查找店铺信息
     * @param shopId
     * @return
     */
    Shop getShopById(Long shopId);

    /**
     * 更新店铺信息，包含对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param filename
     * @return
     */
    ShopExecution updataShop(Shop shop, InputStream shopImgInputStream,String filename);

    /**
     * 添加店铺信息，包含对图片的处理
     * @param shop
     * @param shopImgInputStream
     * @param filename
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename);
}
