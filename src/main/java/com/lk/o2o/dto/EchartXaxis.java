package com.lk.o2o.dto;

import java.util.HashSet;

public class EchartXaxis {
    private String type = "category";
    //为了去重
    private HashSet<String> data;

    public String getType() {
        return type;
    }

    public HashSet<String> getData() {
        return data;
    }

    public void setData(HashSet<String> data) {
        this.data = data;
    }
}
