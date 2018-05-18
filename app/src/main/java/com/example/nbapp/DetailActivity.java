package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.security.AccessController.getContext;

public class DetailActivity extends AppCompatActivity {
    private String TAG="DetailActivity";

    private List<Record> mRecordList=DataSupport.order("date desc").find(Record.class);

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private ImageButton my;

    private TextView tv_allincome;
    private TextView tv_allexpend;

    private TextView tv_income;
    private TextView tv_expend;

    private double allincome=0;
    private double allexpend=0;

    private ImageButton bt_calender;
    private TextView tv_show_date;
    private TextView tv_all;
    private int year,month,day;

   //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的position
            final Record record = mRecordList.get(position);
            record.delete();
            Intent intent = new Intent(DetailActivity.this, com.example.nbapp.EditExpendActivity.class);
            startActivity(intent);
           /* AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
            dialog.setMessage("确定删除吗？")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            record.delete();
                            mRecordList=DataSupport.findAll(Record.class);
                            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                            StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                    StaggeredGridLayoutManager.VERTICAL);
                            recyclerView1.setLayoutManager(layoutManager1);
                            DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
                            recyclerView1.setAdapter(adapter1);

                            //算全部收入支出

                            for (Record record : mRecordList) {
                                if(record.getSign()==1){
                                    allincome = allincome + record.getMoney();
                                }
                                if(record.getSign()==2){
                                    allexpend = allexpend + record.getMoney();
                                }

                            }

                            tv_allincome.setText(allincome + "");
                            tv_allexpend.setText(allexpend + "");

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();*/


        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        add = (ImageButton) findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        tv_allexpend = (TextView) findViewById(R.id.detail_allexpend);
        tv_allincome = (TextView) findViewById(R.id.detail_allincome);
        my = (ImageButton) findViewById(R.id.btn_menu_my);
        bt_calender=(ImageButton)findViewById(R.id.record_calender);
        tv_show_date=(TextView)findViewById(R.id.show_date);
        tv_income = (TextView) findViewById(R.id.tv_income);
        tv_expend = (TextView) findViewById(R.id.tv_expend);
        tv_all=(TextView)findViewById(R.id.all);


        detail.setImageResource(R.drawable.detail);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add.setImageResource(R.drawable.back);
                Intent intent = new Intent(DetailActivity.this, com.example.nbapp.AddExpendActivity.class);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, FindActivity.class);
                startActivity(intent);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, com.example.nbapp.UserActivity.class);
                startActivity(intent);
            }
        });


        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Record> mRecordList=DataSupport.order("date desc").find(Record.class);
                RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.VERTICAL);
                recyclerView1.setLayoutManager(layoutManager1);
                DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
                recyclerView1.setAdapter(adapter1);
            }
        });

        //算全部收入支出
        for (Record record : mRecordList) {
            if(record.getSign()==1){
                allincome = allincome + record.getMoney();
            }
            if(record.getSign()==2){
                allexpend = allexpend + record.getMoney();
            }
        }

        tv_allincome.setText(allincome + "");
        tv_allexpend.setText(allexpend + "");

        bt_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                        tv_show_date.setText(year+"-"+(++month)+"-"+day);
                        List<Record>mRecordList=DataSupport.where("date=?",tv_show_date.getText().toString()).find(Record.class);
                        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                StaggeredGridLayoutManager.VERTICAL);
                        recyclerView1.setLayoutManager(layoutManager1);
                        DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
                        recyclerView1.setAdapter(adapter1);
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(DetailActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar), listener, year, month, day) {
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                        if (mSpinners != null) {
                            NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
                            NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                            mSpinners.removeAllViews();
                            if (mMonthSpinner != null) {
                                mSpinners.addView(mMonthSpinner);
                            }
                            if (mYearSpinner != null) {
                                mSpinners.addView(mYearSpinner);
                            }
                        }
                        View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                        if(dayPickerView != null){
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
                    Date mindate=calender.getTime();
                    dialog.getDatePicker().setMinDate(mindate.getTime());
                } catch (ParseException e) {
                    Log.d("DerailAdapter", "onBindViewHolder: "+e.getMessage());
                }

                dialog.show();

            }

        });

        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);
        DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
        recyclerView1.setAdapter(adapter1);
    }

}

    /*public void initRecord(){
        Record record=new Record();
        record.setMoney(0.0);
        record.setDate("2018-5-17");
        record.setIconid(R.drawable.find);
        record.setType("faxian");

        for (int i=0;i<5;i++){
            mRecordList.add(record);
        }
    }*/
       /*View view = this.getLayoutInflater().inflate((R.layout.detail), null);
        ExpendRecordAdapter adapter = new ExpendRecordAdapter(DetailActivity.this, R.layout.detail_record_item,
                mExpendRecordList);
        ListView listview = (ListView) view.findViewById(R.id.List_view_detail);
        listview.setAdapter(adapter);*/


         /*View view= this.getLayoutInflater().inflate((R.layout.detail), null);
        RecyclerView recyclerViewincome = (RecyclerView)view.findViewById(R.id.recycler_view_incomedetail);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerViewincome.setLayoutManager(layoutManager1);
        IncomeRecordAdapter adapter1 = new IncomeRecordAdapter(mRecordList);
        adapter1.getItemCount();
        recyclerViewincome.setAdapter(adapter1);

        Log.d(TAG, "onCreate: RecyclerView finish");*/

        /*RecyclerView recyclerViewexpend = (RecyclerView) view.findViewById(R.id.recycler_view_expenddetail);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewexpend.setLayoutManager(layoutManager2);
        IncomeRecordAdapter adapter2 = new IncomeRecordAdapter();
        recyclerViewexpend.setAdapter(adapter2);*/

        /*View view= this.getLayoutInflater().inflate((R.layout.testpalette), null);
        TestAdapter adapter=new TestAdapter(DetailActivity.this,R.layout.detail_record_item,mRecordList);
        ListView listView=(ListView)view.findViewById(R.id.list_view_incomedetail) ;
        listView.setAdapter(adapter);*/

        /*DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(DetailActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar), listener, year, month, day) {
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        LinearLayout mSpinners = (LinearLayout) findViewById(getContext().getResources().getIdentifier("android:id/pickers", null, null));
                        if (mSpinners != null) {
                            NumberPicker mMonthSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/month", null, null));
                            NumberPicker mYearSpinner = (NumberPicker) findViewById(getContext().getResources().getIdentifier("android:id/year", null, null));
                            mSpinners.removeAllViews();
                            if (mMonthSpinner != null) {
                                mSpinners.addView(mMonthSpinner);
                            }
                            if (mYearSpinner != null) {
                                mSpinners.addView(mYearSpinner);
                            }
                        }
                        View dayPickerView = findViewById(getContext().getResources().getIdentifier("android:id/day", null, null));
                        if(dayPickerView != null){
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
                    Date mindate=calender.getTime();
                    dialog.getDatePicker().setMinDate(mindate.getTime());
                } catch (ParseException e) {
                    Log.d("DerailAdapter", "onBindViewHolder: "+e.getMessage());
                }

                dialog.show();*/



                /*RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                        StaggeredGridLayoutManager.VERTICAL);
                recyclerView1.setLayoutManager(layoutManager1);
                DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
                recyclerView1.setAdapter(adapter1);*/