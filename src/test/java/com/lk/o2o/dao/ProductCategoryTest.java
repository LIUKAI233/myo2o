package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testqueryProductCategoryList(){
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(20L);
        for (ProductCategory pc: productCategoryList) {
            System.out.println(pc.getProductCategoryName());
        }
    }
}
