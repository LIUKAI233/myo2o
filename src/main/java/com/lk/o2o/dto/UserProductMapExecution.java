package com.lk.o2o.dto;

import com.lk.o2o.entity.UserProductMap;
import com.lk.o2o.enums.UserProductMapEnum;

import java.util.List;

public class UserProductMapExecution {
    private int state;
    private String stateInfo;
    private int count;
    private UserProductMap userProductMap;
    private List<UserProductMap> userProductMapList;

    public UserProductMapExecution(UserProductMapEnum userProductMapEnum){
        this.state = userProductMapEnum.getState();
        this.stateInfo = userProductMapEnum.getStateInfo();
    }

    public UserProductMapExecution(UserProductMapEnum userProductMapEnum,UserProductMap userProductMap){
        this.state = userProductMapEnum.getState();
        this.stateInfo = userProductMapEnum.getStateInfo();
        this.userProductMap = userProductMap;
    }

    public UserProductMapExecution(UserProductMapEnum userProductMapEnum,List<UserProductMap> userProductMapList,int count){
        this.state = userProductMapEnum.getState();
        this.stateInfo = userProductMapEnum.getStateInfo();
        this.userProductMapList = userProductMapList;
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

    public UserProductMap getUserProductMap() {
        return userProductMap;
    }

    public void setUserProductMap(UserProductMap userProductMap) {
        this.userProductMap = userProductMap;
    }

    public List<UserProductMap> getUserProductMapList() {
        return userProductMapList;
    }

    public void setUserProductMapList(List<UserProductMap> userProductMapList) {
        this.userProductMapList = userProductMapList;
    }
}
