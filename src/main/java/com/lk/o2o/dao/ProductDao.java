package com.lk.o2o.dao;

import com.lk.o2o.entity.Product;

public interface ProductDao {
    /**
     * 插入店铺商品
     * @param product
     * @return
     */
    int insertProduct(Product product);
}
