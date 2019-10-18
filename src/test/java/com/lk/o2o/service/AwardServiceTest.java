package com.lk.o2o.service;

import com.lk.o2o.BaseTest;
import com.lk.o2o.dto.AwardExecution;
import com.lk.o2o.entity.Award;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AwardServiceTest extends BaseTest {
    @Autowired
    private AwardService awardService;

    @Test
    public void testmodify(){
        Award award = new Award();
        award.setShopId(20L);
        award.setAwardId(9L);
        award.setEnableStatus(0);
        AwardExecution ae = awardService.modifyAward(award,null);
        System.out.println(ae.getState()+"=============="+ae.getStateInfo());
    }
}
