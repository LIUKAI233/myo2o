package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.Area;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;


    @Test
    public void testInsertShop(){
        Shop shop = new Shop();
        Area area = new Area();
        PersonInfo owner = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(3);
        owner.setUserId(11L);
        shopCategory.setShopCategoryId(12);
        shop.setEnableStatus(1);
        shop.setCreateTime(new Date());
        shop.setAdvice("无");
        shop.setArea(area);
        shop.setShopName("tttest");
        shop.setShopCategory(shopCategory);
        shop.setOwner(owner);
        int i = shopDao.insertShop(shop);
        System.out.println(i+"................"+shop.getShopId());
    }

    @Test
    public void testUpdateShop(){
        Shop shop = new Shop();
        shop.setShopId(28);
        shop.setShopName("newtest");
        shop.setLastEditTime(new Date());
        int i = shopDao.updateShop(shop);
        System.out.println(i);
    }
}
