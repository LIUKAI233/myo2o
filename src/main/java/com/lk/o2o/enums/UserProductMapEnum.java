package com.lk.o2o.enums;

public enum UserProductMapEnum {
    SUCCESS(1,"操作成功"),ERROR(-1001,"操作失败"),EMPTY_INFO(-1002,"请传入正确的信息"),EMPTY(0,"查询无结果");
    private int state;
    private String stateInfo;

    private UserProductMapEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*根据传入的状态值查找对应的状态标识*/
    public static String stateInfo(int state){
        for (UserProductMapEnum value : values()) {
            if (value.getState() == state){
                return value.getStateInfo();
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
