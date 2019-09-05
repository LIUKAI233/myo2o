package com.lk.o2o.service.impl;

import com.lk.o2o.dao.ProductCategoryDao;
import com.lk.o2o.dto.ProductCategoryExecution;
import com.lk.o2o.entity.ProductCategory;
import com.lk.o2o.enums.ProductCategoryStateEnum;
import com.lk.o2o.exceptions.ProductCategoryOperationException;
import com.lk.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> queryProductCategory(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution addProductCategorys(List<ProductCategory> productCategoryList) {
        if(productCategoryList != null && productCategoryList.size() > 0){
            try {
                int effectNum = productCategoryDao.insertProductCategoryList(productCategoryList);
                if(effectNum <= 0){
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }
            } catch (Exception e) {
                throw new ProductCategoryOperationException("addProductCategorys error"+e.getMessage());
            }
        }else{
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }
}
