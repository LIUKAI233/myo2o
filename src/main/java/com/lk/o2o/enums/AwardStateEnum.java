package com.lk.o2o.enums;

public enum AwardStateEnum {
    SUCCESS(1,"操作成功"),ERROR(-1001,"操作失败"),EMPTY_INFO(-1002,"关键信息为空");
    //状态标识
    private int state;
    //状态描述
    private String stateInfo;

    private AwardStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    //根据传入的状态标识，查询对应的状态描述
    public static String stateOf(int state){
        for (AwardStateEnum stateEnum : values()) {
            if(stateEnum.getState() == state){
                return stateEnum.getStateInfo();
            }
        }
        return null;
    }

    public int getState(){
        return state;
    }

    public String getStateInfo(){
        return stateInfo;
    }
}
