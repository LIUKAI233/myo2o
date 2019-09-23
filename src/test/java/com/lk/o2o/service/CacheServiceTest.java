package com.lk.o2o.service;

import com.lk.o2o.BaseTest;
import com.lk.o2o.cache.JedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheServiceTest extends BaseTest {

    @Autowired
    private JedisUtil.Keys jedisKey;
    @Autowired
    private CacheService cacheService;

    @Test
    public void testremoveKey(){
        cacheService.removeKey("areaList");
    }
}
