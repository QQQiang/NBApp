package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddExpendActivity extends AppCompatActivity {
    private String TAG = "AddExpendActivity";

    //声明控件
    private Button expend;
    private Button income;
    private ImageButton close;
    private ImageButton save;
    private Button add_date;
    private EditText add_money;
    private ImageView date;

    //日期
    private Calendar cal;
    private int year, month, day;

    //存入的支出项
    private int expend_id;
    private double expend_money;
    private String expend_type;
    private String expend_date;
    private int expend_iconid;
    private List<ExpendType_Icon> mExpendTypeList = DataSupport.findAll(ExpendType_Icon.class);

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的position
            ExpendType_Icon expendtype = mExpendTypeList.get(position);
            expend_type = expendtype.getType();
            expend_iconid = expendtype.getIconid();
            Log.d(TAG, "onItemClickListener: "+position+" "+expend_iconid );

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expend);

        //定义控件
        income = (Button) findViewById(R.id.btn_add_income);
        expend = (Button) findViewById(R.id.btn_add_expend);
        close = (ImageButton) findViewById(R.id.btn_close);
        save = (ImageButton) findViewById(R.id.save);
        add_date = (Button) findViewById(R.id.add_date);
        add_money = (EditText) findViewById(R.id.add_money);
        date = (ImageView) findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar maxcalendar=Calendar.getInstance();
                maxcalendar.set(2010, 01, 01);
                Date maxdate=maxcalendar.getTime();
                Calendar mincalendar=Calendar.getInstance();
                mincalendar.set(2018, 12, 30);
                Date mindate=mincalendar.getTime();

                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        add_date.setText(year + "-" + (++month) + "-" + day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };


                DatePickerDialog dialog = new DatePickerDialog(AddExpendActivity.this, 0, listener, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                /*dialog.getDatePicker().setMaxDate(maxdate.getTime());
                dialog.getDatePicker().setMinDate(mindate.getTime());
                dialog.getDatePicker().setBackgroundResource(R.color.sandybrown);*/
                dialog.show();
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExpendActivity.this, AddIncomeActivity.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddExpendActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expend_date = add_date.getText().toString();
                expend_money = Double.parseDouble(add_money.getText().toString());

                ExpendRecord expendRecord = new ExpendRecord();
                expendRecord.setDate(expend_date);
                expendRecord.setMoney(expend_money);
                expendRecord.setType(expend_type);
                expendRecord.setIconid(expend_iconid);
                expendRecord.save();


                Intent intent = new Intent(AddExpendActivity.this, DetailActivity.class);
                intent.putExtra("addexpendmoney",expend_money);
                startActivity(intent);
            }
        });

        //加入支出类型recyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_expendtype);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ExpendType_IconAdapter adapter = new ExpendType_IconAdapter(mExpendTypeList, onRecyclerviewItemClickListener);
        recyclerView.setAdapter(adapter);


    }

    //获取当前日期
    private void getDate() {
        cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);       //获取年月日时分秒
        month = cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day = cal.get(Calendar.DAY_OF_MONTH);
    }
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




