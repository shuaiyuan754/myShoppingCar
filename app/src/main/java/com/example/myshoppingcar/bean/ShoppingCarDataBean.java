package com.example.myshoppingcar.bean;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShoppingCarDataBean implements Cloneable{
    private int code;
    private List<DatasBean> datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "ShoppingCarDataBean{" +
                "code=" + code +
                ", datas=" + datas +
                '}';
    }

    public static class DatasBean implements Cloneable{

        private String store_id;
        private String store_name;
        private boolean isSelect_shop;      //店铺是否在购物车中被选中
        private List<GoodsBean> goods;

        @NonNull
        @Override
        protected DatasBean clone() throws CloneNotSupportedException {
            return (DatasBean) super.clone();
        }

        public boolean getIsSelect_shop() {
            return isSelect_shop;
        }

        public void setIsSelect_shop(boolean select_shop) {
            isSelect_shop = select_shop;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        @Override
        public String toString() {
            return "DatasBean{" +
                    "store_id='" + store_id + '\'' +
                    ", store_name='" + store_name + '\'' +
                    ", isSelect_shop=" + isSelect_shop +
                    ", goods=" + goods +
                    '}';
        }

        public static class GoodsBean {

            private String goods_id;
            private String goods_image;
            private String goods_name;
            private String goods_num;
            private String goods_price;
            private boolean isSelect;        //商品是否在购物车中被选中

            public boolean getIsSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean isSelect) {
                this.isSelect = isSelect;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            @Override
            public String toString() {
                return "GoodsBean{" +
                        "goods_id='" + goods_id + '\'' +
                        ", goods_image='" + goods_image + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_num='" + goods_num + '\'' +
                        ", goods_price='" + goods_price + '\'' +
                        ", isSelect=" + isSelect +
                        '}';
            }
        }
    }

}
