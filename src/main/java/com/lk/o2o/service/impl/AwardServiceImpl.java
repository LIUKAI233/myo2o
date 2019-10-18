package com.lk.o2o.service.impl;

import com.lk.o2o.dao.AwardDao;
import com.lk.o2o.dto.AwardExecution;
import com.lk.o2o.dto.ImageHolder;
import com.lk.o2o.entity.Award;
import com.lk.o2o.enums.AwardStateEnum;
import com.lk.o2o.service.AwardService;
import com.lk.o2o.util.FileUtil;
import com.lk.o2o.util.ImageUtil;
import com.lk.o2o.util.pageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private AwardDao awardDao;

    @Autowired
    private void setAwardDao(AwardDao awardDao){
        this.awardDao = awardDao;
    }

    /**
     * 查询奖品集合
     * @param award 查询条件
     * @param pageIndex 查询第几页
     * @param pageSize 一页多少数据
     * @return 查询结果
     */
    @Override
    public AwardExecution queryAwardList(Award award, int pageIndex, int pageSize) {
        int rowIndex = pageCalculator.calculator(pageIndex, pageSize);
        List<Award> awards = awardDao.queryAwardList(award, rowIndex, pageSize);
        int count = awardDao.queryAwardsCount(award);
        if (awards == null || count ==0){
            return new AwardExecution(AwardStateEnum.ERROR);
        }
        return new AwardExecution(AwardStateEnum.SUCCESS,awards,count);
    }

    /**
     * 根据id查询对应的奖品信息
     * @param awardId 奖品id
     * @param shopId 店铺id
     * @return 查询结果
     */
    @Override
    public Award queryAwardById(Long awardId, Long shopId) {
        return awardDao.queryAwardById(awardId,shopId);
    }

    /**
     * 添加奖品
     * @param award 需要添加的奖品信息
     * @param imageHolder 奖品图片信息
     * @return 处理结果
     */
    @Override
    @Transactional
    public AwardExecution addAward(Award award, ImageHolder imageHolder) {
        if (imageHolder.getImageName() != null && imageHolder.getImage() != null && award.getPoint() != null){
            //处理图片
            try {
                addAwardImg(award,imageHolder);
            }catch (Exception e){
                throw new RuntimeException("addAwardImg ERROR"+e.getMessage());
            }
            //添加初始值
            award.setCreateTime(new Date());
            award.setLastEditTime(new Date());
            award.setEnableStatus(1);
            int effctNum = awardDao.insertAward(award);
            if (effctNum <= 0){
                return new AwardExecution(AwardStateEnum.ERROR);
            }else {
                return new AwardExecution(AwardStateEnum.SUCCESS);
            }
        }else {
            return new AwardExecution(AwardStateEnum.EMPTY_INFO);
        }
    }


    /**
     * 修改奖品信息
     * @param award 需要更改的奖品信息
     * @param imageHolder 奖品图片信息
     * @return 处理结果
     */
    @Override
    @Transactional
    public AwardExecution modifyAward(Award award, ImageHolder imageHolder) {
        if (award.getAwardId() != null && award.getShopId() != null){
            int effectNum;
            if (imageHolder != null && imageHolder.getImage() != null && imageHolder.getImageName() != null){
                try {
                    //表示需要更新奖品图片
                    //先删除原先的奖品图片
                    FileUtil.deleteFile(award.getAwardImg());
                    //然后处理修改后的图片
                    addAwardImg(award, imageHolder);
                }catch (Exception e){
                    throw new RuntimeException("adout img ERROR"+e.getMessage());
                }
                effectNum = awardDao.updateAward(award, award.getShopId());
            }else{
                //表示不需要更新奖品图片
                effectNum = awardDao.updateAward(award, award.getShopId());
            }
            if (effectNum <= 0){
                return new AwardExecution(AwardStateEnum.ERROR);
            }else {
                return new AwardExecution(AwardStateEnum.SUCCESS);
            }
        }else {
            return new AwardExecution(AwardStateEnum.EMPTY_INFO);
        }
    }



    @Override
    public AwardExecution deleteAward(Long awardId, Long shopId) {
        int effectNum = awardDao.deleteAward(awardId, shopId);
        if (effectNum <= 0){
            return new AwardExecution(AwardStateEnum.ERROR);
        }
        return new AwardExecution(AwardStateEnum.SUCCESS);
    }

    private void addAwardImg(Award award, ImageHolder imageHolder) {
        //获取图片存储的相对路径(不含文件名和文件后缀)
        String imagePath = FileUtil.getShopImagePath(award.getShopId());
        //存储图片，并返回图片存储位置的绝对路径(含文件名和文件后缀)
        String imgAddr = ImageUtil.generateThumbnail(imageHolder, imagePath);
        award.setAwardImg(imgAddr);
    }
}
