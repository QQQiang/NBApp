package com.example.nbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class AddExpendActivity extends AppCompatActivity {
    private Button expend;
    private  Button income;
    private ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expend);

        //为标题栏注册点击事件
        income=(Button) findViewById(R.id.btn_add_income);
        expend=(Button)findViewById(R.id.btn_add_expend);
        close=(ImageButton)findViewById(R.id.btn_close);

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddExpendActivity.this,AddIncomeActivity.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddExpendActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

        //加入支出类型recyclerView

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view_expendtype);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ExpendType_IconAdapter adapter=new ExpendType_IconAdapter();
        recyclerView.setAdapter(adapter);

    }

     /*ExpendType_Icon cater=new ExpendType_Icon();
        ExpendType_Icon beaty=new ExpendType_Icon();
        ExpendType_Icon clothes=new ExpendType_Icon();
        ExpendType_Icon communi=new ExpendType_Icon();
        ExpendType_Icon digtal=new ExpendType_Icon();
        ExpendType_Icon donate=new ExpendType_Icon();
        ExpendType_Icon food=new ExpendType_Icon();
        ExpendType_Icon gifts=new ExpendType_Icon();
        ExpendType_Icon investout=new ExpendType_Icon();
        ExpendType_Icon medical=new ExpendType_Icon();
        ExpendType_Icon pet=new ExpendType_Icon();
        ExpendType_Icon play=new ExpendType_Icon();
        ExpendType_Icon travel=new ExpendType_Icon();
        ExpendType_Icon study=new ExpendType_Icon();
        ExpendType_Icon other=new ExpendType_Icon();

        cater.setType("餐饮");
        clothes.setType("衣服");
        beaty.setType("丽人");
        communi.setType("通讯");
        digtal.setType("数码");
        donate.setType("捐赠");
        food.setType("食物");
        investout.setType("理财");
        gifts.setType("礼物");
        medical.setType("医疗");
        pet.setType("宠物");
        play.setType("娱乐");
        travel.setType("旅行");
        study.setType("学习");
        other.setType("其他");

        cater.setIconid(R.drawable.cater);
        clothes.setIconid(R.drawable.clothes);
        beaty.setIconid(R.drawable.beaty);
        communi.setIconid(R.drawable.communi);
        digtal.setIconid(R.drawable.digital);
        donate.setIconid(R.drawable.donate);
        food.setIconid(R.drawable.food);
        investout.setIconid(R.drawable.investout);
        gifts.setIconid(R.drawable.gifts);
        medical.setIconid(R.drawable.medical);
        pet.setIconid(R.drawable.pet);
        play.setIconid(R.drawable.play);
        travel.setIconid(R.drawable.travel);
        study.setIconid(R.drawable.study);
        other.setIconid(R.drawable.other);

        cater.save();
        clothes.save();
        beaty.save();
        communi.save();
        digtal.save();
        donate.save();
        food.save();
        investout.save();
        gifts.save();
        medical.save();
        pet.save();
        play.save();
        travel.save();
        study.save();
        other.save();*/



}
