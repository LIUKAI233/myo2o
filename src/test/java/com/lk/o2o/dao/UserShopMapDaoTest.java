package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.UserShopMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserShopMapDaoTest extends BaseTest {
    @Autowired
    private UserShopMapDao userShopMapDao;

    @Test
    public void testqueryUserShopMapList(){
        List<UserShopMap> userShopMaps = userShopMapDao.queryUserShopMapList(null, 0, 5);
        for (UserShopMap userShopMap : userShopMaps) {
            System.out.println(userShopMap.getShop().getShopName());
        }
        int i = userShopMapDao.queryUserShopMapCount(null);
        System.out.println(i);
    }

    @Test
    public void testqueryUserShopMapById(){
        UserShopMap userShopMap = userShopMapDao.queryUserShopMapById(10L, 16L);
        System.out.println(userShopMap.getPoint());
    }

    @Test
    public void testinsertUserShopMap(){
        UserShopMap userShopMap = new UserShopMap();
        PersonInfo user = new PersonInfo();
        Shop shop = new Shop();
        user.setUserId(10L);
        shop.setShopId(16L);
        userShopMap.setShop(shop);
        userShopMap.setUser(user);
        userShopMap.setCreateTime(new Date());
        userShopMap.setPoint(12);
        int i = userShopMapDao.insertUserShopMap(userShopMap);
        System.out.println(i);
        System.out.println(userShopMap.getUserShopId());
    }

    @Test
    public void testupdateUserShopMap(){
        UserShopMap userShopMap = new UserShopMap();
        userShopMap.setPoint(8);
        PersonInfo user = new PersonInfo();
        Shop shop = new Shop();
        user.setUserId(10L);
        shop.setShopId(16L);
        userShopMap.setShop(shop);
        userShopMap.setUser(user);
        int i = userShopMapDao.updateUserShopMap(userShopMap);
        System.out.println(userShopMap.getUserShopId());
    }
}
