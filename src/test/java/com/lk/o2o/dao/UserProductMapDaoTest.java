package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.PersonInfo;
import com.lk.o2o.entity.Product;
import com.lk.o2o.entity.Shop;
import com.lk.o2o.entity.UserProductMap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserProductMapDaoTest extends BaseTest {
    @Autowired
    private UserProductMapDao userProductMapDao;

    @Test
    public void testQueryUserProductMapList(){
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo user = new PersonInfo();
        user.setName("i");
        userProductMap.setUser(user);
        Shop shop = new Shop();
        shop.setShopId(20L);
        userProductMap.setShop(shop);
        List<UserProductMap> userProductMaps = userProductMapDao.queryUserProductMapList(userProductMap, 0, 5);
        for (UserProductMap productMap : userProductMaps) {
            System.out.println(productMap.getOperator().getName());
        }
        int i = userProductMapDao.queryUserProductMapCount(userProductMap);
        System.out.println(i);
    }

    @Test
    public void testInsertUserProductMap(){
        UserProductMap userProductMap = new UserProductMap();
        PersonInfo user = new PersonInfo();
        PersonInfo operator = new PersonInfo();
        user.setUserId(10L);
        user.setName("king");
        operator.setUserId(11L);
        operator.setName("音策");
        Shop shop = new Shop();
        shop.setShopId(20L);
        shop.setShopName("香喷喷奶茶店");
        Product product = new Product();
        product.setProductId(15L);
        product.setProductName("鲜榨西瓜汁");
        userProductMap.setUser(user);
        userProductMap.setOperator(operator);
        userProductMap.setShop(shop);
        userProductMap.setProduct(product);
        int i = userProductMapDao.insertUserProductMap(userProductMap);
        System.out.println("订单编号为："+userProductMap.getUserProductId());
        System.out.println(i);
    }
}
