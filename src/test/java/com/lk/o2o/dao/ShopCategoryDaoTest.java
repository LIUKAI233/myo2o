package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory(){
        ShopCategory shopCategory = new ShopCategory();
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(shopCategory);
        for (ShopCategory category : shopCategories ) {
            System.out.println(category);
        }
    }
}
