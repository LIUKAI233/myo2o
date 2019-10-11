package com.lk.o2o.service.impl;

import com.lk.o2o.dao.ShopAuthMapDao;
import com.lk.o2o.dto.ShopAuthMapExecution;
import com.lk.o2o.entity.ShopAuthMap;
import com.lk.o2o.enums.ShopAuthMapStateEnum;
import com.lk.o2o.exceptions.ShopAuthMapOperationException;
import com.lk.o2o.service.ShopAuthMapService;
import com.lk.o2o.util.pageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService {

    private ShopAuthMapDao shopAuthMapDao;

    @Autowired
    private void setShopAuthMapDao(ShopAuthMapDao shopAuthMapDao){
        this.shopAuthMapDao = shopAuthMapDao;
    }

    @Override
    public ShopAuthMapExecution listShopAuthMapListByShopId(Long shopId, Integer pageIndex, Integer pageSize) {
        //判空
        if (shopId != null && pageIndex != null && pageSize != null){
            try {
                int rowIndex = pageCalculator.calculator(pageIndex, pageSize);
                //查询店铺店员权限集合
                List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId, rowIndex, pageSize);
                //查询该店铺下的所有店员的权限总数
                int count = shopAuthMapDao.queryShopAuthMapCount(shopId);
                if (shopAuthMapList != null && shopAuthMapList.size() != 0 && count != 0){
                    return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMapList,count);
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.ERROR);
            }catch (Exception e){
                throw new ShopAuthMapOperationException("listShopAuthMapListByShopId ERROr "+e.getMessage());
            }
        }else{
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.ERROR_ID_NULL);
        }
    }

    @Override
    public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
        return shopAuthMapDao.queryShopAuthMapByShopAuthId(shopAuthId);
    }

    @Override
    @Transactional
    public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) {
        //判断信息是否完整
        if(shopAuthMap != null && shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null &&
                shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null){
            try {
                //附加默认信息
                shopAuthMap.setCreateTime(new Date());
                shopAuthMap.setLastEditTime(new Date());
                shopAuthMap.setTitleFlag(0);
                shopAuthMap.setEnableStatus(1);
                int effectNum = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
                if (effectNum <= 0){
                    return new ShopAuthMapExecution(ShopAuthMapStateEnum.ERROR);
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
            }catch (Exception e){
                throw new ShopAuthMapOperationException("addShopAuthMap ERROR "+e.getMessage());
            }
        }else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.EMPTY_INFO);
        }
    }

    @Override
    @Transactional
    public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) {
        //空值判断
        if(shopAuthMap != null && shopAuthMap.getShopAuthId() != null){
            try {
                int effectNum = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
                if (effectNum <= 0){
                    return new ShopAuthMapExecution(ShopAuthMapStateEnum.ERROR);
                }
                return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS);
            }catch (Exception e){
                throw new ShopAuthMapOperationException("modifyShopAuthMap error "+e.getMessage());
            }
        }else {
            return new ShopAuthMapExecution(ShopAuthMapStateEnum.ERROR_ID_NULL);
        }
    }
}
