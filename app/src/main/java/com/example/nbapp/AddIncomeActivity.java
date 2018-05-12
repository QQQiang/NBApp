package com.example.nbapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.DatePicker;

import java.util.Calendar;

public class AddIncomeActivity extends AppCompatActivity {


    private Button expend;
    private  Button income;
    private ImageButton close;
    private ImageView date;
    private EditText add_date;

    private Calendar cal;
    private int year,month,day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        income=(Button)findViewById(R.id.btn_add_income);
        close=(ImageButton)findViewById(R.id.btn_close);
        expend=(Button)findViewById(R.id.btn_add_expend);
        date=(ImageView)findViewById(R.id.date);
        add_date=(EditText)findViewById(R.id.add_date);

        expend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddIncomeActivity.this,AddExpendActivity.class);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddIncomeActivity.this,DetailActivity.class);
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
                DatePickerDialog dialog=new DatePickerDialog(AddIncomeActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                dialog.show();
            }
        });

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view_expendtype);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        IncomeType_IconAdapter adapter=new IncomeType_IconAdapter();
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
