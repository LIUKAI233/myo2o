package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.Area;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopList(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(8L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        int shopCount = shopDao.queryShopCount(shopCondition);
        System.out.println("shopList :"+shopList.size());
        System.out.println("shopCount :"+shopCount);
    }

    @Test
    public void testQueryByShopId(){
        Long shopId = 15L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("区域名称"+shop.getArea().getAreaName()+shop.getShopCategory().getShopCategoryName());
    }

    @Test
    @Ignore
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
        shop.setShopId(28L);
        shop.setShopName("newtest");
        shop.setLastEditTime(new Date());
        int i = shopDao.updateShop(shop);
        System.out.println(i);
    }
}
