package com.lk.o2o.service;

import com.lk.o2o.dto.AwardExecution;
import com.lk.o2o.dto.ImageHolder;
import com.lk.o2o.entity.Award;

public interface AwardService {

    /**
     * 根据传入的条件查询奖品集合
     * @param award 查询条件
     * @param pageIndex 查询第几页
     * @param pageSize 一页多少数据
     * @return 查询结果
     */
    AwardExecution queryAwardList(Award award,int pageIndex,int pageSize);

    /**
     * 根据传入的奖品id查询奖品信息
     * @param awardId 奖品id
     * @param shopId 商铺id
     * @return 奖品信息
     */
    Award queryAwardById(Long awardId,Long shopId);

    /**
     * 添加奖品信息
     * @param award 需要添加的奖品信息
     * @param imageHolder 奖品图片信息
     * @return 添加结果
     */
    AwardExecution addAward(Award award, ImageHolder imageHolder);

    /**
     * 更改奖品信息
     * @param award 需要更改的奖品信息
     * @param imageHolder 奖品图片信息
     * @param shopId 商铺id
     * @return 更改结果
     */
    AwardExecution modifyAward(Award award,Long shopId,ImageHolder imageHolder);

    /**
     * 删除对应的奖品信息
     * @param awardId 奖品id
     * @param shopId 商铺id
     * @return 删除结果
     */
    AwardExecution deleteAward(Long awardId,Long shopId);
}
