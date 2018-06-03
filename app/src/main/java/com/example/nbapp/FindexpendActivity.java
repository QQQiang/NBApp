package com.example.nbapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.nbapp.GlobalVariabls.getSysMonth;
import static com.example.nbapp.GlobalVariabls.getSysYear;

public class FindexpendActivity extends AppCompatActivity {

    private String TAG="FindexpendActivity";

    private ImageButton close;
    private PieView_expe pieView;
    public  ArrayList<PieData> datas = new ArrayList<>();
    private Button bt_thisyear;
    private Button bt_thismonth;
    private TextView tv_all;

    private double yearexpend;
    private double monthexpend;

    private List<Record>mMExpendList;
    private List<Record>mYExpendList;

    private List<TypeRecord> mMTypeList=new ArrayList<TypeRecord>();
    private List<TypeRecord> mYTypeList =new ArrayList<TypeRecord>() ;
    public int[] mColors=new int[7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findexpend);

        int yellow=getResources().getColor(R.color.yellow);
        int grassgreen=getResources().getColor(R.color.grassgreen);
        int skyblue=getResources().getColor(R.color.skyblue);
        int tomato=getResources().getColor(R.color.tomato);
        int violet=getResources().getColor(R.color.violet);
        int lightsteelblue=getResources().getColor(R.color.lightsteelblue);
        int silver=getResources().getColor(R.color.silver);
        int tan=getResources().getColor(R.color.tan);
        mColors[0]=yellow;mColors[1]=grassgreen;mColors[2]=skyblue;
        mColors[3]=tomato;mColors[4]=lightsteelblue;mColors[5]=silver;
        mColors[6]=tan;

        close=(ImageButton)findViewById(R.id.btn_close);
        pieView=(PieView_expe)findViewById(R.id.pie_view);
        bt_thisyear=(Button)findViewById(R.id.thisyear);
        bt_thismonth=(Button)findViewById(R.id.thismonth);
        tv_all=(TextView)findViewById(R.id.tv_all);




        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FindexpendActivity.this,FindActivity.class);
                startActivity(intent);
            }
        });

        setMTypeList();
        setMPieView();

        bt_thismonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_thisyear.setBackgroundResource(R.color.transparent);
                bt_thismonth.setBackgroundResource(R.color.sandybrown);
                setMTypeList();
                setMPieView();

            }
        });

        bt_thisyear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_thismonth.setBackgroundResource(R.color.transparent);
                bt_thisyear.setBackgroundResource(R.color.sandybrown);
                setYTypeList();
                setYPieView();
            }
        });

    }



    private void setMPieView(){

        if(mMExpendList.isEmpty()){
            return;
        }
        datas.clear();

       for(TypeRecord trecord:mMTypeList) {
           PieData pd = new PieData(trecord.getType(), (float) trecord.getMoney());
           pd.setColor(trecord.getColor());
           datas.add(pd);
       }

        pieView.setData(datas);


        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.find_expend_rv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(FindexpendActivity.this);
        recyclerView1.setLayoutManager(layoutManager1);
        Find_Adapter adapter1 = new Find_Adapter(mMTypeList);
        recyclerView1.setAdapter(adapter1);

    }

    private void setYPieView(){

        if(mYExpendList.isEmpty()){
            return;
        }
        datas.clear();

        for(TypeRecord trecord:mYTypeList) {
            PieData pd = new PieData(trecord.getType(), (float) trecord.getMoney());
            pd.setColor(trecord.getColor());
            datas.add(pd);
        }

        pieView.setData(datas);


        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.find_expend_rv);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(FindexpendActivity.this);
        recyclerView1.setLayoutManager(layoutManager1);
        Find_Adapter adapter1 = new Find_Adapter(mYTypeList);
        recyclerView1.setAdapter(adapter1);


    }

    private void setMTypeList(){
        int i=0;
        boolean iscontanis=false;
        double temp=0;
        monthexpend=0;
        mMExpendList= DataSupport.where("month=? and sign=?", getSysMonth(),2+"").find(Record.class);

        if(mMExpendList.isEmpty()){
            return;
        }

        for(Record record:mMExpendList){
            for(TypeRecord trecord:mMTypeList){
                if(trecord.getType().equals(record.getType())){
                        temp=trecord.getMoney();
                        temp+=record.getMoney();
                        trecord.setMoney(temp);
                        monthexpend+=record.getMoney();
                        iscontanis=true;
                        break;
                }
            }
            if(!iscontanis){
                TypeRecord trecord=new TypeRecord();
                trecord.setType(record.getType());
                trecord.setMoney(record.getMoney());
                int j = i % mColors.length;       //设置颜色
                trecord.setColor(mColors[j]);
                mMTypeList.add(trecord);
                monthexpend+=record.getMoney();
                i++;
            }
        }
        tv_all.setText(monthexpend+"");
    }

    private void setYTypeList(){
        int i=0;
        mYExpendList=DataSupport.where("year=? and sign=?",getSysYear(),2+"").find(Record.class);

        if(mYExpendList.isEmpty()){
            return;
        }


        boolean iscontanis=false;
        double temp=0;
        yearexpend=0;

        for(Record record:mYExpendList){
            for(TypeRecord trecord:mYTypeList){
                if(trecord.getType().equals(record.getType())){
                    temp=trecord.getMoney();
                    temp+=record.getMoney();
                    trecord.setMoney(temp);
                    yearexpend+=record.getMoney();
                    iscontanis=true;
                    break;
                }
            }
            if(!iscontanis){
                TypeRecord trecord=new TypeRecord();
                trecord.setType(record.getType());
                trecord.setMoney(record.getMoney());
                int j = i% mColors.length;       //设置颜色
                trecord.setColor(mColors[j]);
                mYTypeList.add(trecord);
                yearexpend+=record.getMoney();
                i++;
            }
        }
        tv_all.setText(yearexpend+"");

    }
}
