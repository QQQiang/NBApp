package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindActivity extends AppCompatActivity {
    private String TAG="FindAcitvity";

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private ImageButton my;
    private ImageButton bil;

    private PieView pieView;
    private TextView tv_surplus;
    private TextView tv_allexpend;
    private Button editbudget;
    private TextView tv_allbudget;
    private EditText dialog_ed_budget;


    private List<Record> mExpendRecordList;
    private double allexpend;
    private double allbudget;
    private double surplus;

    private  ArrayList<PieData> datas = new ArrayList<>();


    //使用sharedpreferences对象完成记住预算功能
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);

        Log.d(TAG, "onCreate: ");

        View view = this.getLayoutInflater().inflate((R.layout.find), null);

        mExpendRecordList = DataSupport.findAll(Record.class);

        add = (ImageButton)findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        my=(ImageButton)findViewById(R.id.btn_menu_my);
        bil=(ImageButton)findViewById(R.id.btn_menu_bil);

        pieView = (PieView) findViewById(R.id.pie_view);
        tv_surplus = (TextView) findViewById(R.id.surplus);
        tv_allbudget = (TextView) findViewById(R.id.allbudget);
        tv_allexpend = (TextView) findViewById(R.id.allexpend);
        editbudget = (Button) findViewById(R.id.edit_budget);
        dialog_ed_budget = new EditText(FindActivity.this);

        find.setBackgroundResource(R.color.sandybrown);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindActivity.this, com.example.nbapp.DetailActivity.class);
                startActivity(intent);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindActivity.this, com.example.nbapp.UserActivity.class);
                startActivity(intent);
            }
        });

        bil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindActivity.this, com.example.nbapp.BilActivity.class);
                startActivity(intent);
            }
        });

            //算当月全部支出
        Calendar calender = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        for (Record expendRecord : mExpendRecordList) {
            try {
                calender.setTime(sdf.parse(expendRecord.getDate()));
                if ((calender.get(Calendar.MONTH)) == 4) {
                    allexpend = allexpend + expendRecord.getMoney();
                }

            } catch (ParseException e) {
                Log.d(TAG, "onCreate: " + e.getMessage());
            }

        }
        tv_allexpend.setText(allexpend + "");

        //预先设置饼状图
        allbudget = Double.parseDouble(tv_allbudget.getText().toString());
        Log.d(TAG, "allbudget :" + allbudget);
        surplus = allbudget - allexpend;
        Log.d(TAG, "surplus " + surplus);
        tv_surplus.setText(surplus + "");

        PieData pd_surplus = new PieData("sloop", (float) 20);
        PieData pd_expend = new PieData("sloop", (float) 30);
        PieData pd_surplu = new PieData("sloop", (float) 50);
        datas.add(pd_surplus);
        datas.add(pd_expend);
        datas.add(pd_surplu);
        pieView.setData(datas);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setImageResource(R.drawable.back);
                Intent intent = new Intent(FindActivity.this, com.example.nbapp.AddExpendActivity.class);
                startActivity(intent);
            }
        });

        editbudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(FindActivity.this);
                dialog.setTitle("编辑每月预算")
                        .setView(dialog_ed_budget)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (isMoneyNumber(dialog_ed_budget.getText().toString())) {
                                    tv_allbudget.setText(dialog_ed_budget.getText());
                                }

                                allbudget = Double.parseDouble(tv_allbudget.getText().toString());
                                Log.d(TAG, "onResume:allbudget :" + allbudget);
                                surplus = allbudget - allexpend;
                                Log.d(TAG, "onResume: surplus " + surplus);
                                tv_surplus.setText(surplus + "");

                                PieData pd_surplus = new PieData("sloop", (float) surplus);
                                PieData pd_expend = new PieData("sloop", (float) allexpend);
                                datas.add(pd_surplus);
                                datas.add(pd_expend);
                                pieView.setData(datas);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

        });

    }

    public static boolean isMoneyNumber(String str){
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

}
