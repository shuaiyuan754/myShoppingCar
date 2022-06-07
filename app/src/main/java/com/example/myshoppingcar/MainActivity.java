package com.example.myshoppingcar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.myshoppingcar.adapter.ShoppingCarAdapter;
import com.example.myshoppingcar.bean.ShoppingCarDataBean;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

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
        Gson gson = new Gson();
        ShoppingCarDataBean shoppingCarDataBean = gson.fromJson(data, ShoppingCarDataBean.class);
        storeList = shoppingCarDataBean.getDatas();
        Log.d(TAG, "store: " + storeList);
        Log.d(TAG, "good: " + storeList.get(1).getGoods());



        init();

        initExpandableListView();

    }


    private void init(){
        listView = this.findViewById(R.id.elv_id);
//        listView.setAdapter();
    }

    private void initExpandableListView(){
        ShoppingCarAdapter adapter = new ShoppingCarAdapter(storeList);
        listView.setAdapter(adapter);


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
}





