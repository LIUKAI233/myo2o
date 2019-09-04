package com.lk.o2o.service;

import com.lk.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> queryProductCategory(Long shopId);
}
