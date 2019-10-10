package com.lk.o2o.dto;

import com.lk.o2o.entity.ShopAuthMap;
import com.lk.o2o.enums.ShopAuthMapStateEnum;

import java.util.List;

public class ShopAuthMapExecution {
    //状态标识
    private int state;
    //状态描述
    private String stateInfo;
    //店员权限数量
    private int count;
    //店铺店员权限实体类
    private ShopAuthMap shopAuthMap;
    //店铺店员权限实体类集合
    private List<ShopAuthMap> shopAuthMapList;

    public ShopAuthMapExecution(){

    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum){
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum,ShopAuthMap shopAuthMap){
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
        this.shopAuthMap = shopAuthMap;
    }

    public ShopAuthMapExecution(ShopAuthMapStateEnum shopAuthMapStateEnum,List<ShopAuthMap> shopAuthMapList,int count){
        this.state = shopAuthMapStateEnum.getState();
        this.stateInfo = shopAuthMapStateEnum.getStateInfo();
        this.shopAuthMapList = shopAuthMapList;
        this.count = count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShopAuthMap getShopAuthMap() {
        return shopAuthMap;
    }

    public void setShopAuthMap(ShopAuthMap shopAuthMap) {
        this.shopAuthMap = shopAuthMap;
    }

    public List<ShopAuthMap> getShopAuthMapList() {
        return shopAuthMapList;
    }

    public void setShopAuthMapList(List<ShopAuthMap> shopAuthMapList) {
        this.shopAuthMapList = shopAuthMapList;
    }
}
