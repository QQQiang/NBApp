package com.example.nbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AddExpendActivity extends AppCompatActivity {

    private ExpendType_Icon cater=new ExpendType_Icon();
    private ExpendType_Icon beaty=new ExpendType_Icon();
    private ExpendType_Icon clothes=new ExpendType_Icon();
    private ExpendType_Icon communi=new ExpendType_Icon();
    private ExpendType_Icon digtal=new ExpendType_Icon();
    private ExpendType_Icon donate=new ExpendType_Icon();
    private ExpendType_Icon food=new ExpendType_Icon();
    private ExpendType_Icon gifts=new ExpendType_Icon();
    private ExpendType_Icon investout=new ExpendType_Icon();
    private ExpendType_Icon medical=new ExpendType_Icon();
    private ExpendType_Icon pet=new ExpendType_Icon();
    private ExpendType_Icon play=new ExpendType_Icon();
    private ExpendType_Icon travel=new ExpendType_Icon();
    private ExpendType_Icon study=new ExpendType_Icon();
    private ExpendType_Icon other=new ExpendType_Icon();

    private Button expend;
    private  Button income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expend);

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

        income=findViewById(R.id.btn_add_income);


    }
}
