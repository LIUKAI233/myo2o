package com.lk.o2o.dao;

import com.lk.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * 批量插入商品图片
     * @param productImgList
     * @return
     */
    int insertProductImgs(List<ProductImg> productImgList);
}
