package com.example.myshoppingcar.bean;

import com.google.gson.annotations.SerializedName;

public class GoodBean {
    @SerializedName("goods_id")
    private String goodId;
    @SerializedName("goods_image")
    private String goodImage;
    @SerializedName("goods_name")
    private String goodName;
    @SerializedName("goods_num")
    private String goodNum;
    @SerializedName("goods_price")
    private String goodPrice;

    public GoodBean() {
    }

    public GoodBean(String goodId, String goodImage, String goodName, String goodNum, String goodPrice) {
        this.goodId = goodId;
        this.goodImage = goodImage;
        this.goodName = goodName;
        this.goodNum = goodNum;
        this.goodPrice = goodPrice;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodImage() {
        return goodImage;
    }

    public void setGoodImage(String goodImage) {
        this.goodImage = goodImage;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Override
    public String toString() {
        return "GoodBean{" +
                "goodId='" + goodId + '\'' +
                ", goodImage='" + goodImage + '\'' +
                ", goodName='" + goodName + '\'' +
                ", goodNum='" + goodNum + '\'' +
                ", goodPrice='" + goodPrice + '\'' +
                '}';
    }
}
