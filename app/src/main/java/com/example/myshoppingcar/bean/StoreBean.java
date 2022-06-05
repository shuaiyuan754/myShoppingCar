package com.example.myshoppingcar.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StoreBean {
    @SerializedName("goods")
    private List<GoodBean> goodList;
    @SerializedName("store_id")
    private String storeId;
    @SerializedName("store_name")
    private String storeName;

    public StoreBean() {
    }

    public StoreBean(List<GoodBean> goodList, String storeId, String storeName) {
        this.goodList = goodList;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public List<GoodBean> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<GoodBean> goodList) {
        this.goodList = goodList;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "StoreBean{" +
                "goodList=" + goodList +
                ", storeId='" + storeId + '\'' +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
