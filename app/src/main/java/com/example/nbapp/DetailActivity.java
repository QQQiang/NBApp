package com.example.nbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private String TAG="DetailActivity";

    private List<IncomeRecord> mIncomeRecordList=DataSupport.findAll(IncomeRecord.class);
    private List<ExpendRecord> mExpendRecordList=DataSupport.findAll(ExpendRecord.class);

    private ImageButton detail;
    private ImageButton add;
    private ImageButton find;
    private TextView tv_allincome;
    private TextView tv_allexpend;

    private double allincome=0;
    private double allexpend=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Log.i(TAG, "onCreate: ");

        add = (ImageButton) findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find=(ImageButton)findViewById(R.id.btn_menu_find);
        tv_allexpend = (TextView) findViewById(R.id.detail_allexpend);
        tv_allincome = (TextView) findViewById(R.id.detail_allincome);


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

        for (IncomeRecord incomeRecord : mIncomeRecordList) {
            allincome = allincome + incomeRecord.getMoney();
        }

        for (ExpendRecord expendRecord : mExpendRecordList) {
            allexpend = allexpend + expendRecord.getMoney();
        }

        tv_allincome.setText(allincome + "");
        tv_allexpend.setText(allexpend + "");

        /*View view = this.getLayoutInflater().inflate((R.layout.detail), null);
        ExpendRecordAdapter adapter = new ExpendRecordAdapter(DetailActivity.this, R.layout.detail_record_item,
                mExpendRecordList);
        ListView listview = (ListView) view.findViewById(R.id.List_view_detail);
        listview.setAdapter(adapter);*/

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