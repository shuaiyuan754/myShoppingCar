package com.example.myshoppingcar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myshoppingcar.MainActivity;
import com.example.myshoppingcar.R;
import com.example.myshoppingcar.bean.ShoppingCarDataBean;

import java.text.DecimalFormat;
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
    private double all;
    private Button commit;
    private Button delete;
    private Context context;
//    private ArrayList<Boolean> selects = new ArrayList<>();
    //    private ViewHolder holder;


    public ShoppingCarAdapter(Context context, List<ShoppingCarDataBean.DatasBean> datasBeans, TextView selectAll, TextView allPrice, Button commit, Button delete) {
        this.datasBeans = datasBeans;
        this.selectAll = selectAll;
        this.allPrice = allPrice;
        this.commit = commit;
        this.delete = delete;
        this.context = context;
    }


    public void setData(List<ShoppingCarDataBean.DatasBean> data) {
        this.datasBeans = data;
        notifyDataSetChanged();
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


        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //创建临时的List，用于存储有商品被选中的店铺
                List<ShoppingCarDataBean.DatasBean> tempStores = new ArrayList<>();
                for (int i = 0; i < datasBeans.size(); i++) {
                    //店铺中是否有商品被选中
                    boolean hasGoodsSelect = false;
                    //创建临时的List，用于存储被选中的商品
                    List<ShoppingCarDataBean.DatasBean.GoodsBean> tempGoods = new ArrayList<>();

                    ShoppingCarDataBean.DatasBean storesBean = datasBeans.get(i);
                    List<ShoppingCarDataBean.DatasBean.GoodsBean> goods = storesBean.getGoods();
                    for (int y = 0; y < goods.size(); y++) {
                        ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = goods.get(y);
                        boolean isSelect = goodsBean.getIsSelect();
                        if (isSelect) {
                            hasGoodsSelect = true;
                            tempGoods.add(goodsBean);
                        }
                    }

                    if (hasGoodsSelect) {
                        ShoppingCarDataBean.DatasBean storeBean = new ShoppingCarDataBean.DatasBean();
                        storeBean.setStore_id(storesBean.getStore_id());
                        storeBean.setStore_name(storesBean.getStore_name());
                        storeBean.setGoods(tempGoods);

                        tempStores.add(storeBean);
                    }
                }

                if (tempStores != null && tempStores.size() > 0) {//如果有被选中的
                    /**
                     * 实际开发中，如果有被选中的商品，
                     * 则跳转到确认订单页面，完成后续订单流程。
                     */
                    Toast.makeText(context, "拿到数据跳转订单界面", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "请选择要购买的商品", Toast.LENGTH_SHORT).show();
                }
                
                
                
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

//        Log.d(TAG, "--------test--------: " + datasBeans.get(1).toString());




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



        //合计
        all = 0.0;
        allPrice.setText("合计： ￥0.00");
        for (int x = 0; x < datasBeans.size(); x++) {
            List<ShoppingCarDataBean.DatasBean.GoodsBean> goods = datasBeans.get(x).getGoods();
            for (int y = 0; y < goods.size(); y++) {
                ShoppingCarDataBean.DatasBean.GoodsBean good = goods.get(y);
                boolean isSelect = good.getIsSelect();
                if (isSelect) {
                    String num = good.getGoods_num();
                    String price = good.getGoods_price();

                    double v = Double.parseDouble(num);
                    double v1 = Double.parseDouble(price);

                    all = all + v * v1;

                    @SuppressLint("DefaultLocale") String finalPrice = String.format("%.2f", all);
                    allPrice.setText("合计： ￥" + finalPrice);
                }
            }
        }



//        boolean isSelect = datasBean.getGoods().get(i1).getIsSelect();
//        Log.d(TAG, "getChildView: ===num===" + selects.size());

//        allPrice.setText("0000");
//        all = 0;
//        if (isSelect) {
//            double goods_price = Double.parseDouble(datasBean.getGoods().get(i1).getGoods_price());
//            int num = Integer.parseInt(goodNum.getText().toString());
//            double a = goods_price * num;
//            all = all + a;
//
//
//            @SuppressLint("DefaultLocale") String finalPrice = String.format("%.2f", all);
//            Log.d(TAG, "getChildView: ---------------0607------------" + goods_price + "*" + num);
//
//
//            allPrice.setText("合计： ￥" + finalPrice);
//
//
//
//
//        }不可取，一直再循环内部


//        }else {
//            allPrice.setText("合计： ￥0.00");
//        }


        //删除的点击事件
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 实际开发中，通过回调请求后台接口实现删除操作
                 */
                if (mListener != null) {
                    mListener.delete();
                }
            }
        });


        return itemViewChild;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    //接口
    public interface OnDeleteListener{
        void delete();
    }

    public void setOnDeleteListener(OnDeleteListener listener){
        mListener = listener;
    }

    public OnDeleteListener mListener;




}



