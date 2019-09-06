package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.Product;
import com.lk.o2o.entity.ProductCategory;
import com.lk.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct(){
        Product product = new Product();
        product.setProductName("测试商品");
        product.setProductDesc("这是测试商品的描述");
        product.setNormalPrice("15");
        product.setPromotionPrice("12");
        product.setPriority(15);
        product.setImgAddr("adaa");
        product.setCreateTime(new Date());
        product.setEnableStatus(0);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(11L);
        product.setProductCategory(pc);
        Shop shop = new Shop();
        shop.setShopId(20L);
        product.setShop(shop);
        int i = productDao.insertProduct(product);
        System.out.println("i的值为"+i);
        System.out.println(product.getProductId());
    }
}
