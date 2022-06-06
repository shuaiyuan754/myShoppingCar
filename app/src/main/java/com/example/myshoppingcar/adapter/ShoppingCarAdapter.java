package com.example.myshoppingcar.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshoppingcar.R;
import com.example.myshoppingcar.bean.ShoppingCarDataBean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * @author Administrator
 * @creat time 2022/6/6 11:43
 * description:
 **/
public class ShoppingCarAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ShoppingCarAdapter";

    private final List<ShoppingCarDataBean.DatasBean> datasBeans;
    private Boolean isSelect = false;

    public ShoppingCarAdapter(List<ShoppingCarDataBean.DatasBean> datasBeans) {
        this.datasBeans = datasBeans;
    }

    @Override
    public int getGroupCount() {
        if ( datasBeans == null){
            return 0;
        }
        return datasBeans.size();
    }

    @Override
    public int getChildrenCount(int i) {
        if (datasBeans.get(i).getGoods() == null){
            return 0;
        }
        return datasBeans.get(i).getGoods().size();
    }

    @Override
    public Object getGroup(int i) {
        return datasBeans.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return datasBeans.get(i).getGoods().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group,viewGroup,false);
        ShoppingCarDataBean.DatasBean datasBean = datasBeans.get(i);
        List<ShoppingCarDataBean.DatasBean.GoodsBean> goodList = datasBean.getGoods();
        String storeId = datasBean.getStore_id();
        String storeName = datasBean.getStore_name();
        Boolean isSelect = datasBean.getIsSelect_shop();
        Log.d(TAG, "-------" + storeId);

        ImageView selectIcon = itemView.findViewById(R.id.select_id);
        TextView store = itemView.findViewById(R.id.store_name);
        store.setText(storeName);

        selectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (!isSelect){
//                    selectIcon.setImageResource(R.drawable.select);
//                    updateSelect(i,true);
//                    return;
//                }
//                selectIcon.setImageResource(R.drawable.inselect);
//                updateSelect(i,false);
//                notifyDataSetChanged();
            }
        });


        return itemView;
    }


    private void updateSelect(int position, Boolean isSelect){
        datasBeans.get(position).setIsSelect_shop(isSelect);
    }



    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child, viewGroup, false);
        ShoppingCarDataBean.DatasBean datasBean = datasBeans.get(i);
        ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = datasBean.getGoods().get(i1);

        ImageView select = itemView.findViewById(R.id.select_id);
        TextView goodName = itemView.findViewById(R.id.textView1);
        TextView goodPrice = itemView.findViewById(R.id.textView2);
        TextView goodNum = itemView.findViewById(R.id.textView3);
        ImageView minus = itemView.findViewById(R.id.minus_id);
        ImageView plus = itemView.findViewById(R.id.plus_id);

        goodName.setText(goodsBean.getGoods_name());
        goodPrice.setText("￥" + goodsBean.getGoods_price());
        goodPrice.setTextColor(R.color.red);
        goodNum.setText("0");

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodNum.getText().toString());
                if (num > 0){
                    num = num - 1;
                    goodNum.setText(String.valueOf(num));
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodNum.getText().toString());
                num = num + 1;
                goodNum.setText(String.valueOf(num));
            }
        });


        return itemView;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
