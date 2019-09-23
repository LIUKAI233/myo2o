package com.lk.o2o.enums;

public enum LocalAuthStateEnum {
    ERR_PASSWORD(-1,"密码错误"),NULL_USERID(-2,"USERID为空");
    private int state;
    private String stateInfo;

    private LocalAuthStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /*根据传入的状态值查询对应的状态信息*/
    public static LocalAuthStateEnum stateOf(int state){
        for (LocalAuthStateEnum se : values()) {
            if(se.getState() == state){
                return se;
            }
        }
        return null;
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
}
