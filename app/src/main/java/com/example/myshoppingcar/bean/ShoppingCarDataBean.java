package com.example.myshoppingcar.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingCarDataBean {
    @SerializedName("code")
    private int code;
    @SerializedName("datas")
    private List<StoreBean> storeList;

    public ShoppingCarDataBean() {
    }

    public ShoppingCarDataBean(int code, List<StoreBean> storeList) {
        this.code = code;
        this.storeList = storeList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<StoreBean> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreBean> storeList) {
        this.storeList = storeList;
    }

    @Override
    public String toString() {
        return "ShoppingCarDataBean{" +
                "code=" + code +
                ", storeList=" + storeList +
                '}';
    }
}
