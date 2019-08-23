package com.lk.o2o.service;

import com.lk.o2o.dto.ShopExecution;
import com.lk.o2o.entity.Shop;

import java.io.InputStream;

public interface ShopService {
    /**
     * 传入shop和图片流，添加商铺信息
     * @param shop
     * @param shopImg
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename);
}
