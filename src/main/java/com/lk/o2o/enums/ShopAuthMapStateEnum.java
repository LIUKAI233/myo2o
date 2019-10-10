package com.lk.o2o.enums;

public enum ShopAuthMapStateEnum {
    SUCCESS(1,"操作成功"),ERROR(-1001,"操作失败"),ERROR_ID_NULL(-1002,"传入错误的id"),EMPTY_INFO(-1003,"请输入完整的信息");

    //状态标识
    private int state;
    //状态描述
    private String stateInfo;

    private ShopAuthMapStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    //通过传入的状态标识获取状态描述
    public static String stateOf(int state){
        for (ShopAuthMapStateEnum stateEnum : values()) {
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
