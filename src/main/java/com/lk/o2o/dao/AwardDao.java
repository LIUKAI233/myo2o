package com.lk.o2o.dao;

import com.lk.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作奖品
 */
public interface AwardDao {
    /**
     * 根据传入的条件查询奖品集合
     * @param awardCondition  查询条件
     * @param rowIndex  从第几条开始查询
     * @param pageSize  返回几条
     * @return 查询的集合
     */
    List<Award> queryAwardList(@Param("awardCondition") Award awardCondition,@Param("rowIndex") Integer rowIndex,@Param("pageSize") Integer pageSize);

    /**
     * 符合查询条件的奖品数量
     * @param awardCondition 查询条件
     * @return 查询结果数量
     */
    int queryAwardsCount(@Param("awardCondition") Award awardCondition);

    /**
     * 根据传入的id值查询奖品信息
     * @param awardId 奖品id
     * @param shopId 店铺id
     * @return 奖品信息
     */
    Award queryAwardById(@Param("awardId")Long awardId,@Param("shopId")Long shopId);

    /**
     * 插入奖品信息
     * @param award 奖品信息
     * @return 处理结果
     */
    int insertAward(Award award);

    /**
     * 根据传入的奖品信息，修改
     * @param award  需要更新的奖品信息
     * @return 处理结果
     */
    int updateAward(@Param("award") Award award,@Param("shopId")Long shopId);

    /**
     * 删除奖品信息
     * @param awardId 奖品id
     * @param shopId  店铺id
     * @return 处理结果
     */
    int deleteAward(@Param("awardId")Long awardId,@Param("shopId")Long shopId);
}
