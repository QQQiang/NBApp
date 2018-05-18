package com.example.nbapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditIncomeActivity extends AppCompatActivity {

    private String TAG = "AddIncomeActivity";

    //声明控件
    private Button expend;
    private Button income;
    private ImageButton close;
    private ImageButton save;
    private Button add_date;
    private EditText add_money;
    private ImageView date;

    private Calendar cal;
    private int year,month,day;

    //存入的支出项
    private int income_id;
    private double income_money;
    private String income_type;
    private String income_date;
    private int income_iconid;
    private List<IncomeType_Icon> mIncomeTypeList = DataSupport.findAll(IncomeType_Icon.class);

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onRecyclerviewItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的positiondn
            Log.d(TAG, "onItemClickListener: "+position+" "+income_type);
            IncomeType_Icon expendtype = mIncomeTypeList.get(position);
            income_type = expendtype.getType();
            income_iconid = expendtype.getIconid();


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_income);



        income=(Button)findViewById(R.id.btn_add_income);
        close=(ImageButton)findViewById(R.id.btn_close);
        expend=(Button)findViewById(R.id.btn_add_expend);
        save = (ImageButton) findViewById(R.id.save);
        date=(ImageView)findViewById(R.id.date);
        add_date=(Button)findViewById(R.id.add_date);
        add_money = (EditText) findViewById(R.id.add_money);

        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditIncomeActivity.this,AddExpendActivity.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditIncomeActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        add_date.setText(year+"-"+(++month)+"-"+day);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(EditIncomeActivity.this, AlertDialog.THEME_HOLO_LIGHT, listener, year, month, day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                income_date = add_date.getText().toString();
                income_money = Double.parseDouble(add_money.getText().toString());

                Record record = new Record();
                record.setDate(income_date);
                record.setMoney(income_money);
                record.setType(income_type);
                record.setIconid(income_iconid);
                record.setSign(1);
                record.save();

                Intent intent = new Intent(EditIncomeActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });


        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view_expendtype);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        IncomeType_IconAdapter adapter=new IncomeType_IconAdapter(mIncomeTypeList, onRecyclerviewItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }


    /*IncomeType_Icon salary=new IncomeType_Icon();
        IncomeType_Icon partjob=new IncomeType_Icon();
        IncomeType_Icon investin=new IncomeType_Icon();
        IncomeType_Icon redpacket=new IncomeType_Icon();
        IncomeType_Icon other=new IncomeType_Icon();
        salary.setType("工资");
        partjob.setType("兼职");
        investin.setType("理财");
        redpacket.setType("红包");
        other.setType("其他");

        salary.setIconid(R.drawable.salary);
        partjob.setIconid(R.drawable.partjob);
        investin.setIconid(R.drawable.investin);
        redpacket.setIconid(R.drawable.redpacket);
        other.setIconid(R.drawable.other);

        salary.save();
        partjob.save();
        investin.save();
        redpacket.save();
        other.save();*/
}


