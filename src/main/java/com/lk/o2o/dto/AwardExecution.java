package com.lk.o2o.dto;

import com.lk.o2o.entity.Award;
import com.lk.o2o.enums.AwardStateEnum;

import java.util.List;

public class AwardExecution {
    //状态标识
    private int state;
    //状态描述
    private String stateInfo;
    //奖品数量
    private int count;
    //奖品实体类
    private Award award;
    //奖品实体类集合
    private List<Award> awardList;

    public AwardExecution(){

    }

    public AwardExecution(AwardStateEnum awardStateEnum){
        this.state = awardStateEnum.getState();
        this.stateInfo = awardStateEnum.getStateInfo();
    }

    public AwardExecution(AwardStateEnum awardStateEnum,Award award){
        this.state = awardStateEnum.getState();
        this.stateInfo = awardStateEnum.getStateInfo();
        this.award = award;
    }

    public AwardExecution(AwardStateEnum awardStateEnum,List<Award> awardList,int count){
        this.state = awardStateEnum.getState();
        this.stateInfo = awardStateEnum.getStateInfo();
        this.awardList = awardList;
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

    public Award getAward() {
        return award;
    }

    public void setAward(Award award) {
        this.award = award;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }
}
