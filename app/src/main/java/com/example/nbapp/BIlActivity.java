package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BilActivity extends AppCompatActivity {
    private String TAG = "BilActivty";

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private ImageButton my;
    private ImageButton bil;
    private TextView show_year;
    private TextView tv_year_surp;
    private TextView tv_year_income;
    private TextView tv_year_expend;

    private int year, month, day;
    private double year_surp, year_income, year_expend;
    private double month_surp, month_income, month_expend;

    private List<Record> mRecordList;
    private List<MonthRecord> mMonthRecordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bil);

        add = (ImageButton) findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        bil = (ImageButton) findViewById(R.id.btn_menu_bil);
        my = (ImageButton) findViewById(R.id.btn_menu_my);
        show_year = (TextView) findViewById(R.id.show_year);
        tv_year_surp = (TextView) findViewById(R.id.year_surplus);
        tv_year_expend = (TextView) findViewById(R.id.year_expend);
        tv_year_income = (TextView) findViewById(R.id.year_income);

        bil.setBackgroundResource(R.color.sandybrown);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setImageResource(R.drawable.back);
                Intent intent = new Intent(BilActivity.this, com.example.nbapp.AddExpendActivity.class);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BilActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BilActivity.this, com.example.nbapp.UserActivity.class);
                startActivity(intent);
            }
        });

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BilActivity.this, com.example.nbapp.DetailActivity.class);
                startActivity(intent);
            }
        });


        show_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        show_year.setText(year + "");
                        showBil();
                    }

                };

                DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(BilActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar), listener, year, month, day) {
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                        if (mSpinners != null) {
                            NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                            mSpinners.removeAllViews();

                            if (mYearSpinner != null) {
                                mSpinners.addView(mYearSpinner);
                            }
                        }
                        View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                        if (dayPickerView != null) {
                            dayPickerView.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onDateChanged(DatePicker view, int year, int month, int day) {
                        super.onDateChanged(view, year, month, day);
                        setTitle("请选择显示日期");
                    }
                };
                dialog.setTitle("请选择显示日期");

                //设置日期最大值
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000L);

                //设置日期最小值
                Calendar calender = Calendar.getInstance();// 获得一个日历的实例
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    calender.setTime(sdf.parse("2000-01-01"));
                    Date mindate = calender.getTime();
                    dialog.getDatePicker().setMinDate(mindate.getTime());
                } catch (ParseException e) {

                }

                dialog.show();
            }
        });
        showBil();
    }

    public void createMonthRecord() {

        for (int year = 2000; year <= 2018; year++) {
            for (int i = 1; i <= 12; i++) {
                month_expend = 0;
                month_income = 0;
                month_surp = 0;
                mRecordList = DataSupport.where("year=? and month=?", year + "", i + "").find(Record.class);
                for (Record record : mRecordList) {
                    if (record.getSign() == 1) {
                        Log.d(TAG, "showBil: " + record.getMoney());
                        month_income += record.getMoney();
                    }
                    if (record.getSign() == 2) {
                        month_expend += record.getMoney();
                        Log.d(TAG, "showBil: " + record.getMoney());
                    }
                }
                month_surp = month_income - month_expend;
                MonthRecord monthRecord = new MonthRecord();
                monthRecord.setMonth(i);
                monthRecord.setYear(year);
                monthRecord.setExpend(month_expend);
                monthRecord.setIncome(month_income);
                monthRecord.setSurplus(month_surp);
                monthRecord.save();

            }
        }
    }

    private void showBil() {
        int year = Integer.parseInt(show_year.getText().toString());

        year_income = 0;
        year_expend = 0;
        year_surp = 0;

        for (int i = 1; i <= 12; i++) {
            month_expend = 0;
            month_income = 0;
            month_surp = 0;

            mRecordList = DataSupport.where("year=? and month=?", year + "", i + "").find(Record.class);
            for (Record record : mRecordList) {
                if (record.getSign() == 1) {
                    Log.d(TAG, "showBil: " + record.getMoney());
                    month_income += record.getMoney();
                }
                if (record.getSign() == 2) {
                    month_expend += record.getMoney();
                    Log.d(TAG, "showBil: " + record.getMoney());
                }
            }
            month_surp = month_income - month_expend;
            MonthRecord monthRecord = new MonthRecord();
            monthRecord.setMonth(i);
            monthRecord.setYear(year);
            monthRecord.setExpend(month_expend);
            monthRecord.setIncome(month_income);
            monthRecord.setSurplus(month_surp);
            monthRecord.updateAll("year=? and month=?", year + "", i + "");

            year_income+=month_income;
            year_expend+=month_expend;
        }


        year_surp=year_income-year_expend;

        tv_year_expend.setText(year_expend+"");
        tv_year_income.setText(year_income+"");
        tv_year_surp.setText(year_surp+"");

        mMonthRecordList=DataSupport.where("year=? ",year+"").order("month desc").find(MonthRecord.class);

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.bil_rv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerView1.setLayoutManager(layoutManager1);
        BilAdapter adapter1 = new BilAdapter(mMonthRecordList);
        recyclerView1.setAdapter(adapter1);

    }

}






