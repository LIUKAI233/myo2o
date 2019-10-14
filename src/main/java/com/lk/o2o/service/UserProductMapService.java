package com.lk.o2o.service;

import com.lk.o2o.dto.UserProductMapExecution;
import com.lk.o2o.entity.UserProductMap;

public interface UserProductMapService {

    /**
     * 根据传入的条件分页查询信息
     * @param userProductMap 查询条件
     * @param pageIndex 查询第几页
     * @param pageSize 一页多少数据
     * @return 查询结果
     */
    UserProductMapExecution listUserProductMaps(UserProductMap userProductMap,Integer pageIndex,Integer pageSize);
}
