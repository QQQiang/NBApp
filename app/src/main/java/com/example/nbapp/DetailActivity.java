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

    private List<Record> mRecordList;

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private ImageButton my;
    private ImageButton bil;

    private TextView tv_allincome;
    private TextView tv_allexpend;

    private TextView tv_income;
    private TextView tv_expend;

    private double allincome=0;
    private double allexpend=0;

    private ImageButton bt_calender;
    private TextView tv_show_date;
    private int year,month,day;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        add = (ImageButton) findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        bil=(ImageButton)findViewById(R.id.btn_menu_bil);
        my = (ImageButton) findViewById(R.id.btn_menu_my);
        bt_calender=(ImageButton)findViewById(R.id.record_calender);
        tv_show_date=(TextView)findViewById(R.id.show_date);
        tv_income = (TextView) findViewById(R.id.tv_income);
        tv_expend = (TextView) findViewById(R.id.tv_expend);
        tv_allexpend = (TextView) findViewById(R.id.detail_allexpend);
        tv_allincome = (TextView) findViewById(R.id.detail_allincome);


        detail.setBackgroundResource(R.color.sandybrown);

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

        bil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, com.example.nbapp.BilActivity.class);
                startActivity(intent);
            }
        });



        bt_calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                        tv_show_date.setText(year+"-"+(++month));
                        showDetail();

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

        showDetail();

    }

   private void showDetail(){
       String[] date=tv_show_date.getText().toString().split("-");

       mRecordList=DataSupport.where("year=? and month=?",date[0],date[1]).order("date desc").find(Record.class);
       RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
       StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
               StaggeredGridLayoutManager.VERTICAL);
       recyclerView1.setLayoutManager(layoutManager1);
       DetailAdapter adapter1 = new DetailAdapter(mRecordList,onItemClickListener);
       recyclerView1.setAdapter(adapter1);

       allincome=0;
       allexpend=0;

       for (Record mrecord : mRecordList) {
           if(mrecord.getSign()==1){
               allincome = allincome + mrecord.getMoney();
           }
           if(mrecord.getSign()==2){
               allexpend = allexpend + mrecord.getMoney();
           }
       }

       tv_allincome.setText(allincome + "");
       tv_allexpend.setText(allexpend + "");
   }

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, final int position) {
            //这里的view就是我们点击的view  position就是点击的position
            final Record record = mRecordList.get(position);
            final String[] select =new String[] {"删除","编辑"};

            AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
            dialog.setTitle("请选择")
                    .setSingleChoiceItems(select, 0,
                            new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    String sel = select[which];
                                    if(sel.equals("编辑")){
                                        if(record.getSign()==1){
                                            Intent intent = new Intent(DetailActivity.this, EditIncomeActivity.class);
                                            Log.d(TAG, "onClick: "+record.getRecord_id());
                                            intent.putExtra("record_id",record.getRecord_id());
                                            intent.putExtra("record_date",record.getDate());
                                            intent.putExtra("record_money",record.getMoney());
                                            startActivity(intent);
                                            showDetail();
                                        }
                                        if(record.getSign()==2){
                                            Intent intent = new Intent(DetailActivity.this, EditExpendActivity.class);
                                            intent.putExtra("record_id",record.getRecord_id());
                                            intent.putExtra("record_date",record.getDate());
                                            intent.putExtra("record_money",record.getMoney());
                                            startActivity(intent);
                                            showDetail();
                                        }
                                    }else{
                                        AlertDialog.Builder dialogd = new AlertDialog.Builder(DetailActivity.this);
                                        dialogd.setMessage("确定删除吗？")
                                                .setCancelable(false)
                                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        record.delete();
                                                        showDetail();
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
                                    dialog.dismiss();
                                }
                            }
                    )
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();



        }
    };
}




