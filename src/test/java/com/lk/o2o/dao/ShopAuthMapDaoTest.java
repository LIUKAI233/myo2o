package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.ShopAuthMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopAuthMapDaoTest extends BaseTest {
    @Autowired
    private ShopAuthMapDao shopAuthMapDao;

    @Test
    public void testBqueryShopAuthMapListByShopId(){
        List<ShopAuthMap> shopAuthMaps = shopAuthMapDao.queryShopAuthMapListByShopId(28L, 0, 5);
        for (ShopAuthMap shopAuthMap : shopAuthMaps) {
            System.out.println("通过id查询的店铺名称为"+shopAuthMap.getShop().getShopName()+"职位为"+shopAuthMap.getTitle());
        }
    }

    @Test
    public void testAinsertShopAuthMap(){
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        PersonInfo employee = new PersonInfo();
        Shop shop = new Shop();
        employee.setUserId(9L);
        shop.setShopId(28L);
        shopAuthMap.setShop(shop);
        shopAuthMap.setEmployee(employee);
        shopAuthMap.setEnableStatus(0);
        shopAuthMap.setCreateTime(new Date());
        shopAuthMap.setTitle("测试职位");
        shopAuthMap.setTitleFlag(0);
        int i = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
        System.out.println("插入语句影响的行数"+i);
        System.out.println("shopAuthId 为"+shopAuthMap.getShopAuthId());
    }

    @Test
    public void testCupdateShopAuthMap(){
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        shopAuthMap.setShopAuthId(4L);
        shopAuthMap.setTitle("更改后的测试职位");
        int i = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
        System.out.println("更新语句影响的行数"+i);
        System.out.println("shopAuthId 为"+shopAuthMap.getShopAuthId());
    }

    @Test
    public void testEdeleteShopAuthMap(){
        ShopAuthMap shopAuthMap = new ShopAuthMap();
        shopAuthMap.setShopAuthId(4L);
        int i = shopAuthMapDao.deleteShopAuthMap(shopAuthMap.getShopAuthId());
        System.out.println("删除语句影响的行数"+i);
        System.out.println("shopAuthId 为"+shopAuthMap.getShopAuthId());
    }

    @Test
    public void testDqueryShopAuthMapByShopAuthId(){
        ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapByShopAuthId(4L);
        System.out.println("经过更改后的职位名称"+shopAuthMap.getTitle());
    }
}
