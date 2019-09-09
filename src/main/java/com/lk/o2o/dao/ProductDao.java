package com.lk.o2o.dao;

import com.lk.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {
    /**
     * 插入店铺商品
     * @param product 商品信息
     * @return 影响的行数
     */
    int insertProduct(Product product);

    /**
     * 根据传入的商品信息，更改商品
     * @param product 商品信息
     * @return 影响的行数
     */
    int updataProduct(Product product);

    /**
     * 查询店铺商品
     * @param product 查询条件
     * @param rowIndex 从第几条数据开始
     * @param pageSize 一页多少数据
     * @return 查询出来的集合
     */
    List<Product> selectProduct(@Param("product")Product product, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);
}