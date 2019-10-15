package com.lk.o2o.service.impl;

import com.lk.o2o.dao.ProductSellDailyDao;
import com.lk.o2o.entity.ProductSellDaily;
import com.lk.o2o.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {

    private static Logger log = LoggerFactory.getLogger(ProductSellDailyServiceImpl.class);

    @Autowired
    private ProductSellDailyDao productSellDailyDao;

    @Override
    public void dailyCalculate() {
        log.info("Quartz Runner");
        //统计在tb_user_product_map里面产生销量的每个店铺的各件商品的日销量
        productSellDailyDao.insertProductSellDaily();
        //统计余下的商品的日销量，全部置为0(为了迎合echarts的数据请求)
        productSellDailyDao.insertDefaultProductSellDaily();
        //System.out.println("Quartz跑起来了");
    }

    @Override
    public List<ProductSellDaily> queryProductSellDailyList(ProductSellDaily productSellDaily, Date beginTime, Date endTime) {
        return productSellDailyDao.queryProductSellDailyList(productSellDaily,beginTime,endTime);
    }
}
