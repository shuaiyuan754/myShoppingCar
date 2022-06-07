package com.example.myshoppingcar.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myshoppingcar.R;
import com.example.myshoppingcar.bean.ShoppingCarDataBean;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * @author Administrator
 * @creat time 2022/6/6 11:43
 * description:
 **/
public class ShoppingCarAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ShoppingCarAdapter";

    private TextView allPrice;
    private Boolean isSelectAll = false;
    private TextView selectAll;
    private List<ShoppingCarDataBean.DatasBean> datasBeans;
    private double all = 0;
    private int n = 0;
//    private ArrayList<Boolean> selects = new ArrayList<>();
    //    private ViewHolder holder;


    public ShoppingCarAdapter(List<ShoppingCarDataBean.DatasBean> datasBeans,TextView selectAll,TextView allPrice) {
        this.datasBeans = datasBeans;
        this.selectAll = selectAll;
        this.allPrice = allPrice;
    }

//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView selectIcon;
//        public ViewHolder(View itemView){
//            super(itemView);
//            this.selectIcon = itemView.findViewById(R.id.select_id);
//        }
//    }
//
//    public static class ViewHolderChild  {
//        ImageView selectIconChild;
//        public ViewHolderChild(View itemView){
//            this.selectIconChild = itemView.findViewById(R.id.select_id);
//        }
//    }

//    static class GroupViewHolder {
//        @SuppressLint("NonConstantResourceId")
//        @BindView(R.id.select_id)
//        ImageView ivSelect;
//
//
//        GroupViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }


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
//        GroupViewHolder holder;
//        ViewHolder holder;
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group,viewGroup,false);
//        holder = new GroupViewHolder(itemView);
//        holder = new ViewHolder(itemView);
        ShoppingCarDataBean.DatasBean datasBean = datasBeans.get(i);
        List<ShoppingCarDataBean.DatasBean.GoodsBean> goodList = datasBean.getGoods();
        String storeId = datasBean.getStore_id();
        String storeName = datasBean.getStore_name();
        Boolean isSelect = datasBean.getIsSelect_shop();//isSelect不可以在下面的点击事件用，因为这个值在下面用就是固定值

        Log.d(TAG, "-------" + storeId);

        ImageView selectIcon = itemView.findViewById(R.id.select_id);
        TextView store = itemView.findViewById(R.id.store_name);
        store.setText(storeName);



        //店铺内的商品都选中的时候，店铺的也要选中
        for (int j = 0; j < datasBean.getGoods().size(); j++) {
            ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = datasBean.getGoods().get(j);
            boolean Select = goodsBean.getIsSelect();
            if (Select) {
                datasBean.setIsSelect_shop(true);
            } else {
                datasBean.setIsSelect_shop(false);
                break;
            }
        }

//        holder = new ViewHolder(itemView);

        //因为set之后要重新get，所以这一块代码要放到一起执行
        //店铺是否在购物车中被选中
        final boolean isSelect_shop = datasBean.getIsSelect_shop();
        if (isSelect_shop) {
            Log.d(TAG, "true: ");
            selectIcon.setImageResource(R.drawable.select);
        } else {
            Log.d(TAG, "false: ");
            selectIcon.setImageResource(R.drawable.inselect);
        }
//        notifyDataSetChanged();

//        for (int j = 0; j < datasBean.getGoods().size(); j++) {
//            boolean select = datasBean.getGoods().get(i).getIsSelect();
//            if (select){
//
//                datasBean.setIsSelect_shop(true);
//            }else {
//                datasBean.setIsSelect_shop(false);
//                break;
//            }
//        }
//        if (datasBean.getIsSelect_shop()){
//            selectIcon.setImageResource(R.drawable.select);
//        }else {
//            selectIcon.setImageResource(R.drawable.inselect);
//        }



        selectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelect = datasBean.getIsSelect_shop();
                datasBean.setIsSelect_shop(!isSelect);
                Log.d(TAG, "onClick: " + datasBean.getIsSelect_shop());

                for (int i = 0; i < datasBean.getGoods().size(); i++) {
                    datasBean.getGoods().get(i).setIsSelect(!isSelect);
                }
                notifyDataSetChanged();

//                if (datasBean.getIsSelect_shop()){
//                    selectIcon.setImageResource(R.drawable.select);
//
//                }else {
//                    selectIcon.setImageResource(R.drawable.inselect);
//                }







//                boolean isSelect = datasBean.getIsSelect_shop();
//                Log.d(TAG, "onClick: " + isSelect);
//                if (!isSelect){
//                    selectIcon.setImageResource(R.drawable.select);
//                    datasBean.setIsSelect_shop(true);
//
//                    return;
//                }
//                selectIcon.setImageResource(R.drawable.inselect);
//                datasBean.setIsSelect_shop(false);
            }
        });

        //全选
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                for (int j = 0; j < datasBeans.size(); j++) {
//                    datasBeans.get(j).setIsSelect_shop(!isSelectAll);
//                }
//                notifyDataSetChanged();


                isSelectAll = !isSelectAll;

                for (int i = 0; i < datasBeans.size(); i++) {
                    List<ShoppingCarDataBean.DatasBean.GoodsBean> goods = datasBeans.get(i).getGoods();
                    for (int j = 0; j < goods.size(); j++) {
                        ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = goods.get(j);
                        goodsBean.setIsSelect(isSelectAll);
                    }
                }

                notifyDataSetChanged();


            }
        });




        return itemView;
    }


//    private void updateSelect(int position, Boolean isSelect){
//        datasBeans.get(position).setIsSelect_shop(isSelect);
//    }



    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View itemViewChild = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_child, viewGroup, false);
        ShoppingCarDataBean.DatasBean datasBean = datasBeans.get(i);
        ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = datasBean.getGoods().get(i1);

        ImageView select = itemViewChild.findViewById(R.id.select_id);
        TextView goodName = itemViewChild.findViewById(R.id.textView1);
        TextView goodPrice = itemViewChild.findViewById(R.id.textView2);
        TextView goodNum = itemViewChild.findViewById(R.id.textView3);
        ImageView minus = itemViewChild.findViewById(R.id.minus_id);
        ImageView plus = itemViewChild.findViewById(R.id.plus_id);

        goodNum.setText(datasBean.getGoods().get(i1).getGoods_num());
        goodName.setText(goodsBean.getGoods_name());
        goodPrice.setText("￥" + goodsBean.getGoods_price());
//        goodPrice.setTextColor(R.color.red);
//        goodNum.setText("1");

        Log.d(TAG, "--------test--------: " + datasBeans.get(1).toString());



        if (datasBeans.get(i).getGoods().get(i1).getIsSelect()){
            Log.d(TAG, "getChildView: \\\\\\\\\\\\\\" + i + "||" + i1 + "t" + datasBeans.get(i).getGoods().get(i1).getIsSelect());
            select.setImageResource(R.drawable.select);
        }else {
            Log.d(TAG, "getChildView: \\\\\\\\\\\\\\" + i + "||" + i1 + "f" + datasBeans.get(i).getGoods().get(i1).getIsSelect());
            select.setImageResource(R.drawable.inselect);
        }

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodNum.getText().toString());
                if (num > 1){
                    num = num - 1;
                    datasBean.getGoods().get(i1).setGoods_num(num + "");
                    goodNum.setText(String.valueOf(num));
                    Log.d(TAG, "_______num-_______: " + datasBean.getGoods().get(i1).getGoods_num());
                }
                notifyDataSetChanged();

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(goodNum.getText().toString());
                num = num + 1;
                datasBean.getGoods().get(i1).setGoods_num(num + "");
                goodNum.setText(String.valueOf(num));
                Log.d(TAG, "_______num+_______: " + datasBean.getGoods().get(i1).getGoods_num());
                notifyDataSetChanged();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelect = goodsBean.getIsSelect();
                goodsBean.setIsSelect(!isSelect);
                if (goodsBean.getIsSelect()){
                    select.setImageResource(R.drawable.select);
                }else {
                    select.setImageResource(R.drawable.inselect);
                }
                notifyDataSetChanged();

//                if (!isSelect){
//                    select.setImageResource(R.drawable.select);
//                    goodsBean.setIsSelect(true);
//                    return;
//                }
//                select.setImageResource(R.drawable.inselect);
//                goodsBean.setIsSelect(false);

            }
        });

//        //对应
//        if (datasBean.getIsSelect_shop()){
//            select.setImageResource(R.drawable.select);
//            datasBean.getGoods().get(i1).setIsSelect(true);
//        }


        boolean isSelect = datasBean.getGoods().get(i1).getIsSelect();

//
//        selects.add(isSelect);
//        Log.d(TAG, "getChildView: ====Bollean====" + selects);
//
//        for (int j = 0; j < selects.size(); j++) {
//            if (selects.get(j)){
//                break;
//            }
//            allPrice.setText("合计： ￥0.00");
//        }

//        Log.d(TAG, "getChildView: ===num===" + selects.size());

        if (isSelect) {
            double goods_price = Double.parseDouble(datasBean.getGoods().get(i1).getGoods_price());
            int num = Integer.parseInt(goodNum.getText().toString());
            double a = goods_price * num;
            all = all + a;


            @SuppressLint("DefaultLocale") String finalPrice = String.format("%.2f", all);
            Log.d(TAG, "getChildView: ---------------0607------------" + goods_price + "*" + num);


            allPrice.setText("合计： ￥" + finalPrice);

            all = 0;


        }


//        }else {
//            allPrice.setText("合计： ￥0.00");
//        }


        return itemViewChild;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
