package com.example.myshoppingcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshoppingcar.adapter.ShoppingCarAdapter;
import com.example.myshoppingcar.bean.ShoppingCarDataBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<ShoppingCarDataBean.DatasBean> datasTemp;
    private int n = 0;
    private Context context = this;
    private String data = "{\n" +
            "    \"code\": 200,\n" +
            "    \"datas\": [\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"111111\",\n" +
            "                    \"goods_image\": \"http://172.22.32.176:8000/media/goodpic/4.png\",\n" +
            "                    \"goods_name\": \"长江三号\",\n" +
            "                    \"goods_num\": \"1\",\n" +
            "                    \"goods_price\": \"21.50\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"1\",\n" +
            "            \"store_name\": \"一号小书店\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"222221\",\n" +
            "                    \"goods_image\": \"http://file06.16sucai.com/2016/0511/9711205e4c003182edeed83355e6f1c7.jpg\",\n" +
            "                    \"goods_name\": \"马克思传\",\n" +
            "                    \"goods_num\": \"2\",\n" +
            "                    \"goods_price\": \"28.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"222222\",\n" +
            "                    \"goods_image\": \"http://img01.taopic.com/150424/240473-1504240U13615.jpg\",\n" +
            "                    \"goods_name\": \"我和霍金的生活\",\n" +
            "                    \"goods_num\": \"2\",\n" +
            "                    \"goods_price\": \"228.00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"2\",\n" +
            "            \"store_name\": \"二号中书店\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"333331\",\n" +
            "                    \"goods_image\": \"http://pic22.nipic.com/20120718/8002769_100147127333_2.jpg\",\n" +
            "                    \"goods_name\": \"心的重建\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"38.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"333332\",\n" +
            "                    \"goods_image\": \"http://pic.58pic.com/58pic/14/71/50/40e58PICy54_1024.jpg\",\n" +
            "                    \"goods_name\": \"福尔摩斯\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"338.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"333333\",\n" +
            "                    \"goods_image\": \"http://img01.taopic.com/150518/318750-15051PS40671.jpg\",\n" +
            "                    \"goods_name\": \"书法常识\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"3.80\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"3\",\n" +
            "            \"store_name\": \"三号大书店\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private ExpandableListView listView;
    private List<ShoppingCarDataBean.DatasBean> storeList;
    private TextView selectAll;
    private TextView allPrice;
    private Button commit;
    private TextView update;
    private TextView edit;
    private TextView blank;
    private Button delete;
    private ShoppingCarAdapter adapter;
    private LinearLayout ll;
    private LinearLayout background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        String data = "{\n" +
//                "    \"code\": 200,\n" +
//                "    \"datas\": [\n" +
//                "        {\n" +
//                "            \"goods\": [\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"111111\",\n" +
//                "                    \"goods_image\": \"http://pic.58pic.com/58pic/15/62/69/34K58PICbmZ_1024.jpg\",\n" +
//                "                    \"goods_name\": \"习近平谈治国理政(第二卷)(平装)\",\n" +
//                "                    \"goods_num\": \"1\",\n" +
//                "                    \"goods_price\": \"18.00\"\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"store_id\": \"1\",\n" +
//                "            \"store_name\": \"一号小书店\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"goods\": [\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"222221\",\n" +
//                "                    \"goods_image\": \"http://file06.16sucai.com/2016/0511/9711205e4c003182edeed83355e6f1c7.jpg\",\n" +
//                "                    \"goods_name\": \"马克思传\",\n" +
//                "                    \"goods_num\": \"2\",\n" +
//                "                    \"goods_price\": \"28.00\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"222222\",\n" +
//                "                    \"goods_image\": \"http://img01.taopic.com/150424/240473-1504240U13615.jpg\",\n" +
//                "                    \"goods_name\": \"我和霍金的生活\",\n" +
//                "                    \"goods_num\": \"2\",\n" +
//                "                    \"goods_price\": \"228.00\"\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"store_id\": \"2\",\n" +
//                "            \"store_name\": \"二号中书店\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"goods\": [\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"333331\",\n" +
//                "                    \"goods_image\": \"http://pic22.nipic.com/20120718/8002769_100147127333_2.jpg\",\n" +
//                "                    \"goods_name\": \"心的重建\",\n" +
//                "                    \"goods_num\": \"3\",\n" +
//                "                    \"goods_price\": \"38.00\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"333332\",\n" +
//                "                    \"goods_image\": \"http://pic.58pic.com/58pic/14/71/50/40e58PICy54_1024.jpg\",\n" +
//                "                    \"goods_name\": \"福尔摩斯\",\n" +
//                "                    \"goods_num\": \"3\",\n" +
//                "                    \"goods_price\": \"338.00\"\n" +
//                "                },\n" +
//                "                {\n" +
//                "                    \"goods_id\": \"333333\",\n" +
//                "                    \"goods_image\": \"http://img01.taopic.com/150518/318750-15051PS40671.jpg\",\n" +
//                "                    \"goods_name\": \"书法常识\",\n" +
//                "                    \"goods_num\": \"3\",\n" +
//                "                    \"goods_price\": \"3.80\"\n" +
//                "                }\n" +
//                "            ],\n" +
//                "            \"store_id\": \"3\",\n" +
//                "            \"store_name\": \"三号大书店\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";

        initData();


        init();

        initExpandableListView();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                initExpandableListView();
                ll.setVisibility(View.VISIBLE);
                blank.setVisibility(View.GONE);
                delete.setVisibility(View.GONE);
                allPrice.setVisibility(View.VISIBLE);
                commit.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                background.setVisibility(View.GONE);
                edit.setText("编辑");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText = edit.getText().toString();
                if (editText.equals("编辑")) {
                    edit.setText("完成");
                    allPrice.setVisibility(View.GONE);
                    commit.setVisibility(View.GONE);
                    blank.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                } else if (editText.equals("完成")) {

                    blank.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    allPrice.setVisibility(View.VISIBLE);
                    commit.setVisibility(View.VISIBLE);
                    edit.setText("编辑");
                }
            }
        });

    }

    private void initData() {
        Gson gson = new Gson();
        ShoppingCarDataBean shoppingCarDataBean = gson.fromJson(data, ShoppingCarDataBean.class);
        storeList = shoppingCarDataBean.getDatas();
        Log.d(TAG, "store: " + storeList);
        Log.d(TAG, "good: " + storeList.get(1).getGoods());

    }


    private void init(){
        listView = this.findViewById(R.id.elv_id);
        selectAll = this.findViewById(R.id.selectAll_id);
        allPrice = this.findViewById(R.id.allPrice_id);
        commit = this.findViewById(R.id.btn_id);
        update = this.findViewById(R.id.update_id);
        edit = this.findViewById(R.id.edit_id);
        blank = this.findViewById(R.id.blank_id);
        delete = this.findViewById(R.id.btnDelete_id);
        ll = this.findViewById(R.id.bottom_id);
        background = this.findViewById(R.id.background_id);
//        listView.setAdapter();
    }

    private void initExpandableListView(){
        adapter = new ShoppingCarAdapter(context,storeList,selectAll,allPrice,commit,delete);
        listView.setAdapter(adapter);

        adapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {


            @Override
            public void delete() {

                //判断是否有店铺或商品被选中
                //true为有，则需要刷新数据；反之，则不需要；
                boolean hasSelect = false;
                //创建临时的List，用于存储没有被选中的购物车数据
                datasTemp = new ArrayList<>();

                for (int i = 0; i < storeList.size(); i++) {
                    List<ShoppingCarDataBean.DatasBean.GoodsBean> goods = storeList.get(i).getGoods();
                    boolean isSelect_shop = storeList.get(i).getIsSelect_shop();

                    if (isSelect_shop) {
                        hasSelect = true;
                        //跳出本次循环，继续下次循环。
                        continue;
                    } else {
                        try {
                            datasTemp.add(storeList.get(i).clone());
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        datasTemp.get(datasTemp.size() - 1).setGoods(new ArrayList<ShoppingCarDataBean.DatasBean.GoodsBean>());
                    }

                    for (int y = 0; y < goods.size(); y++) {
                        ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = goods.get(y);
                        boolean isSelect = goodsBean.getIsSelect();

                        if (isSelect) {
                            hasSelect = true;
                        } else {
                            datasTemp.get(datasTemp.size() - 1).getGoods().add(goodsBean);
                        }
                        n = n + 1;



//                        if (datasTemp.size() == 0){
//                            ll.setVisibility(View.GONE);
//                        }
                    }
                }
                Log.d(TAG, "falseNum: " + n);


                if (hasSelect) {
//                    showDeleteDialog(datasTemp);
                    dialog();
                } else {
                    Toast.makeText(MainActivity.this, "请选择", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //展开所有组
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

    }

    public void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background)
                .setTitle("WARNING!")
                .setMessage("确定要删除商品吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        storeList = datasTemp;
                        adapter.setData(storeList);
                        if (n == 0){
                            ll.setVisibility(View.GONE);
                            listView.setVisibility(View.GONE);
                            background.setVisibility(View.VISIBLE);
                        }
                        n = 0;
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }
}





