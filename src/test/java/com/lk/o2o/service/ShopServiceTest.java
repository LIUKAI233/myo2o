package com.lk.o2o.service;

import com.lk.o2o.BaseTest;
import com.lk.o2o.dto.ShopExecution;
import com.lk.o2o.entity.Area;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testUpdataShop() throws FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId(28);
        shop.setShopName("更改店铺");
        File shopImg = new File("F:\\new.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.updataShop(shop, is, shopImg.getName());
        System.out.println(shopExecution.getStateInfo());
    }

    @Test
    @Ignore
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        Area area = new Area();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(3);
        owner.setUserId(11L);
        shopCategory.setShopCategoryId(12);
        shop.setAdvice("无");
        shop.setArea(area);
        shop.setShopName("ttteeeest");
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        File shopImg = new File("F:\\test.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop,is, shopImg.getName());
        System.out.println(shopExecution.getShop().getShopId());
    }
}
