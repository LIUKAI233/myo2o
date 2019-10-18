package com.lk.o2o.dao;

import com.lk.o2o.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserShopMapDao {

    /**
     * 根据传入的条件返回顾客店铺积分情况
     * @param userShopMap 查询条件
     * @param rowIndex 从那条数据开始查询
     * @param pageSize 一共查询多少条数据
     * @return 查询结果
     */
    List<UserShopMap> queryUserShopMapList(@Param("userShopMap")UserShopMap userShopMap,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

    /**
     * 插叙符合条件的数据总数
     * @param userShopMap 查询条件
     * @return 查询结果
     */
    int queryUserShopMapCount(@Param("userShopMap")UserShopMap userShopMap);

    /**
     * 根据传入的id查询用户在某个店铺的积分
     * @param userId 用户id
     * @param shopId 店铺id
     * @return 查询结果
     */
    UserShopMap queryUserShopMapById(@Param("userId")Long userId,@Param("shopId")Long shopId);

    /**
     * 添加一条店铺用户的积分记录
     * @param userShopMap 店铺用户的积分记录
     * @return 处理结果
     */
    int insertUserShopMap(UserShopMap userShopMap);

    /**
     * 更新用户在某店铺的积分
     * @param userShopMap 店铺用户的积分记录
     * @return 处理结果
     */
    int updateUserShopMap(UserShopMap userShopMap);
}
