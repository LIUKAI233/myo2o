package com.lk.o2o.service.impl;

import com.lk.o2o.dao.UserProductMapDao;
import com.lk.o2o.dto.UserProductMapExecution;
import com.lk.o2o.entity.UserProductMap;
import com.lk.o2o.enums.UserProductMapEnum;
import com.lk.o2o.service.UserProductMapService;
import com.lk.o2o.util.pageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProductMapServiceImpl implements UserProductMapService {

    @Autowired
    private UserProductMapDao userProductMapDao;

    /**
     * 根据传入的条件分页查询信息
     * @param userProductMap 查询条件
     * @param pageIndex 查询第几页
     * @param pageSize 一页多少数据
     * @return 查询结果
     */
    @Override
    public UserProductMapExecution listUserProductMaps(UserProductMap userProductMap, Integer pageIndex, Integer pageSize) {
        //判断空值
        if(userProductMap != null && pageIndex != null && pageSize != null){
            //页转行
            int rowIndex = pageCalculator.calculator(pageIndex, pageSize);
            List<UserProductMap> userProductMaps = userProductMapDao.queryUserProductMapList(userProductMap, rowIndex, pageSize);
            if (userProductMaps.size() == 0){
                return new UserProductMapExecution(UserProductMapEnum.EMPTY);
            }
            int count = userProductMapDao.queryUserProductMapCount(userProductMap);
            return new UserProductMapExecution(UserProductMapEnum.SUCCESS,userProductMaps,count);
        }else{
            return new UserProductMapExecution(UserProductMapEnum.EMPTY_INFO);
        }
    }
}
