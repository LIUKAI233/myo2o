package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testinsertProductCategoryList(){
        ProductCategory pc1 = new ProductCategory();
        pc1.setShopId(16L);
        pc1.setProductCategoryName("ada");
        pc1.setCreateTime(new Date());
        pc1.setPriority(12);
        ProductCategory pc2 = new ProductCategory();
        pc2.setShopId(16L);
        pc2.setProductCategoryName("13da");
        pc2.setCreateTime(new Date());
        pc2.setPriority(15);
        List<ProductCategory> list = new ArrayList<>();
        list.add(pc1);
        list.add(pc2);
        int i = productCategoryDao.insertProductCategoryList(list);
        System.out.println(i);
    }

    @Test
    public void testqueryProductCategoryList(){
        List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(20L);
        for (ProductCategory pc: productCategoryList) {
            System.out.println(pc.getProductCategoryName());
        }
    }
}
