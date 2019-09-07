package com.lk.o2o.service;

import com.lk.o2o.dto.ImageHolder;
import com.lk.o2o.dto.ProductExecution;
import com.lk.o2o.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * 添加商品信息，并处理图片
     * @param product 图片信息
     * @param image 图片信息名称
     * @param imageList 批量图片信息名称
     * @return 处理结果
     */
    ProductExecution addProduct(Product product, ImageHolder image, List<ImageHolder> imageList);
}
