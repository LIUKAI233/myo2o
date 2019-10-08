package com.lk.o2o.dao;

import com.lk.o2o.BaseTest;
import com.lk.o2o.entity.Award;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/*这个注解开启test方法按名字顺序执行*/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest extends BaseTest {
    @Autowired
    private AwardDao awardDao;

    @Test
    public void testAInsertAward(){
        Award award1 = new Award();
        Award award2 = new Award();
        award1.setAwardName("测试奖品1");
        award1.setShopId(20L);
        award1.setPoint(5);
        award1.setEnableStatus(1);
        award2.setAwardName("测试奖品2");
        award2.setShopId(20L);
        award2.setPoint(4);
        award2.setEnableStatus(1);
        int i = awardDao.insertAward(award1);
        System.out.println("第一次插入影响的行数:"+i);
        int i1 = awardDao.insertAward(award2);
        System.out.println("第二次插入影响的行数:"+i1);
    }

    @Test
    public void testBQueryAwardList(){
        Award award = new Award();
        award.setShopId(20L);
        List<Award> awards = awardDao.queryAwardList(award, 0, 3);
        for (Award award1 : awards) {
            System.out.println("查询到集合中的奖品名称:"+award1.getAwardName());
        }
    }

    @Test
    public void testCQueryAwardCount(){
        Award award = new Award();
        award.setShopId(20L);
        int i = awardDao.queryAwardsCount(award);
        System.out.println("符合查询条件的结果:"+i);
    }

    @Test
    public void testEQueryAwardById(){
        Award award = awardDao.queryAwardById(7L, 20L);
        System.out.println("根据id查询到的奖品名称:"+award.getAwardName());
    }

    @Test
    public void testDUpdateAward(){
        Award award = new Award();
        award.setAwardId(7L);
        award.setAwardName("更改后的测试奖品1");
        int i = awardDao.updateAward(award, 20L);
        System.out.println("更改影响的行数:"+i);
    }

    @Test
    public void testFDeleteAward(){
        int i = awardDao.deleteAward(7L, 20L);
        System.out.println("第一次删除影响的行数"+i);
        int i1 = awardDao.deleteAward(8L, 20L);
        System.out.println("第二次删除影响的行数"+i1);
    }
}
