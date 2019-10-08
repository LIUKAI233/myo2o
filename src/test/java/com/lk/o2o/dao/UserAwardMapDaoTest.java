package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.Award;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.UserAwardMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserAwardMapDaoTest extends BaseTest {
    @Autowired
    private UserAwardMapDao userAwardMapDao;

    @Test
    public void testqueryUserAwardMapList(){
        List<UserAwardMap> userAwardMaps = userAwardMapDao.queryUserAwardMapList(null, 0, 5);
        for (UserAwardMap userAwardMap : userAwardMaps) {
            System.out.println(userAwardMap.getUser().getName());
        }
        int i = userAwardMapDao.queryUserAwardMapCount(null);
        System.out.println(i);
    }

    @Test
    public void testqueryUserAwardMapById(){
        UserAwardMap userAwardMap = userAwardMapDao.queryUserAwardMapById(2L);
        System.out.println(userAwardMap.getCreateTime());
    }

    @Test
    public void testinsertUserAwardMap(){
        UserAwardMap userAwardMap = new UserAwardMap();
        Shop shop = new Shop();
        shop.setShopId(20L);
        shop.setShopName("香喷喷奶茶店");
        Award award = new Award();
        award.setAwardId(9L);
        award.setAwardName("测试奖品1");
        PersonInfo user = new PersonInfo();
        PersonInfo operator = new PersonInfo();
        user.setUserId(10L);
        user.setName("king");
        operator.setUserId(11L);
        operator.setName("音策");
        userAwardMap.setAward(award);
        userAwardMap.setOperator(operator);
        userAwardMap.setShop(shop);
        userAwardMap.setUser(user);
        userAwardMap.setCreateTime(new Date());
        userAwardMap.setPoint(10);
        userAwardMap.setUsedStatus(1);
        int i = userAwardMapDao.insertUserAwardMap(userAwardMap);
        System.out.println(userAwardMap.getUserAwardId());
    }

    @Test
    public void testupdateUserAwardMap(){
        PersonInfo user = new PersonInfo();
        user.setUserId(10L);
        UserAwardMap userAwardMap = new UserAwardMap();
        userAwardMap.setUserAwardMapId(2L);
        userAwardMap.setUser(user);
        userAwardMap.setUsedStatus(0);
        int i = userAwardMapDao.updateUserAwardMap(userAwardMap);
        System.out.println(userAwardMap.getUserAwardId());
    }
}
