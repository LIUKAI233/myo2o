package com.lk.o2o.dao;

import com.lk.o2o.entity.UserAwardMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//顾客以领取奖品的相关操作
public interface UserAwardMapDao {

    /**
     * 根据输入条件，查询集合
     * @param userAwardMap 查询条件
     * @param rowIndex 从第几条开始
     * @param pageSize 返回的条数
     * @return 查询到的结果
     */
    List<UserAwardMap> queryUserAwardMapList(@Param("userAwardMap") UserAwardMap userAwardMap,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

    /**
     * 查询符合条件的数据数量
     * @param userAwardMap 查询条件
     * @return 数据数量
     */
    int queryUserAwardMapCount(@Param("userAwardMap")UserAwardMap userAwardMap);

    /**
     * 根据id查询对应的奖品兑换情况
     * @param userAwardId id
     * @return 查询结果
     */
    UserAwardMap queryUserAwardMapById(Long userAwardId);

    /**
     * 添加一条奖品兑换信息
     * @param userAwardMap 奖品兑换信息
     * @return 处理结果
     */
    int insertUserAwardMap(UserAwardMap userAwardMap);

    /**
     * 修改一条奖品兑换信息
     * @param userAwardMap 奖品兑换信息
     * @return 处理结果
     */
    int updateUserAwardMap(UserAwardMap userAwardMap);
}
