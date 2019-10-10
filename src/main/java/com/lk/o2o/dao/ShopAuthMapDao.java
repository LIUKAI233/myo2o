package com.lk.o2o.dao;

import com.lk.o2o.entity.ShopAuthMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopAuthMapDao {

    /**
     * 通过店铺id查询该店铺下所有的员工授权信息
     * @param shopId 店铺id
     * @param rowIndex 从第几条开始查询
     * @param pageSize 查询多少条
     * @return 查询结果
     */
    List<ShopAuthMap> queryShopAuthMapListByShopId(@Param("shopId")Long shopId, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 查询该店铺下的员工授权信息总数
     * @param shopId 店铺id
     * @return 员工授权信息总数
     */
    int queryShopAuthMapCount(@Param("shopId")Long shopId);

    /**
     * 增加员工授权信息
     * @param shopAuthMap 员工授权信息
     * @return 处理结果
     */
    int insertShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 更改员工授权信息
     * @param shopAuthMap 更改后的员工授权信息
     * @return 处理结果
     */
    int updateShopAuthMap(ShopAuthMap shopAuthMap);

    /**
     * 对员工进行除权
     * @param shopAuthId id
     * @return 处理结果
     */
    int deleteShopAuthMap(Long shopAuthId);

    /**
     * 通过shopAuthId查询员工授权信息
     * @param shopAuthId id
     * @return 员工授权信息
     */
    ShopAuthMap queryShopAuthMapByShopAuthId(Long shopAuthId);
}
