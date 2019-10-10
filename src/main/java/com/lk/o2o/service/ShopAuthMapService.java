package com.lk.o2o.service;

import com.lk.o2o.dto.ShopAuthMapExecution;
import com.lk.o2o.entity.ShopAuthMap;

public interface ShopAuthMapService {

    /**
     * 通过店铺id查询店铺员工权限列表
     * @param shopId 店铺id
     * @param pageIndex 查询第几页数据
     * @param pageSize 一页多少条数据
     * @return 查询结果
     */
    ShopAuthMapExecution listShopAuthMapListByShopId(Long shopId,Integer pageIndex,Integer pageSize);

    /**
     * 通过shopAuthId查询对应的权限信息
     * @param shopAuthId id
     * @return 查询结果
     */
    ShopAuthMap getShopAuthMapById(Long shopAuthId);

    /**
     * 添加授权信息
     * @param shopAuthMap 授权信息
     * @return 处理结果
     */
    ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 更新授权信息，包括职位，状态
     * @param shopAuthMap 更改的信息
     * @return 处理结果
     */
    ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap);
}
