package com.lk.o2o.dao;

import com.lk.o2o.entity.UserProductMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserProductMapDao {

    /**
     * 根据传入的条件，查询顾客购买商品的记录
     * @param userProductMap 查询条件
     * @param rowIndex 从第几条开始查询
     * @param pageSize 查询几条
     * @return 查询结果
     */
    List<UserProductMap> queryUserProductMapList(@Param("userProductMap")UserProductMap userProductMap, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 查询符合条件的记录总数
     * @param userProductMap 查询条件
     * @return 记录总数
     */
    int queryUserProductMapCount(@Param("userProductMap")UserProductMap userProductMap);

    /**
     * 插入顾客购买商品的记录
     * @param userProductMap 顾客购买商品的记录
     * @return 处理结果
     */
    int insertUserProductMap(UserProductMap userProductMap);
}
