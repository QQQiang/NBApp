package com.example.nbapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
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
    private ImageButton my;

    private TextView tv_allincome;
    private TextView tv_allexpend;

    private TextView tv_income;
    private TextView tv_expend;

    private double allincome=0;
    private double allexpend=0;

    private ImageButton bt_select;

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onExpendItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的position
            final ExpendRecord expendRecord = mExpendRecordList.get(position);
            AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
            dialog.setMessage("确定删除吗？")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            expendRecord.delete();
                            mExpendRecordList=DataSupport.findAll(ExpendRecord.class);
                            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                            StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                    StaggeredGridLayoutManager.VERTICAL);
                            recyclerView1.setLayoutManager(layoutManager1);
                            ExpendRecordAdapter adapter1 = new ExpendRecordAdapter(mExpendRecordList,onExpendItemClickListener);
                            recyclerView1.setAdapter(adapter1);

                            //算全部收入支出

                            for (ExpendRecord expendRecord : mExpendRecordList) {
                                allexpend = allexpend + expendRecord.getMoney();
                            }

                            tv_allexpend.setText(allexpend + "");
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
    };

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener onIncomeItemClickListener = new OnRecyclerviewItemClickListener() {
        @Override
        public void onItemClickListener(View v, int position) {
            //这里的view就是我们点击的view  position就是点击的position
            final IncomeRecord expendRecord = mIncomeRecordList.get(position);
            AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
            dialog.setMessage("确定删除吗？")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            expendRecord.delete();
                            mIncomeRecordList=DataSupport.findAll(IncomeRecord.class);
                            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                            StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                    StaggeredGridLayoutManager.VERTICAL);
                            recyclerView1.setLayoutManager(layoutManager1);
                            IncomeRecordAdapter adapter1 = new IncomeRecordAdapter(mIncomeRecordList,onIncomeItemClickListener);
                            recyclerView1.setAdapter(adapter1);

                            //算全部收入支出
                            for (IncomeRecord incomeRecord : mIncomeRecordList) {
                                allincome = allincome + incomeRecord.getMoney();
                            }

                            tv_allincome.setText(allincome + "");

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
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Log.i(TAG, "onCreate: ");

        add = (ImageButton) findViewById(R.id.btn_menu_add);
        detail = (ImageButton) findViewById(R.id.btn_menu_detail);
        find = (ImageButton) findViewById(R.id.btn_menu_find);
        tv_allexpend = (TextView) findViewById(R.id.detail_allexpend);
        tv_allincome = (TextView) findViewById(R.id.detail_allincome);
        my = (ImageButton) findViewById(R.id.btn_menu_my);
        bt_select=(ImageButton)findViewById(R.id.record_select);
        tv_income = (TextView) findViewById(R.id.tv_income);
        tv_expend = (TextView) findViewById(R.id.tv_expend);

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

        //算全部收入支出
        for (IncomeRecord incomeRecord : mIncomeRecordList) {
            allincome = allincome + incomeRecord.getMoney();
        }

        for (ExpendRecord expendRecord : mExpendRecordList) {
            allexpend = allexpend + expendRecord.getMoney();
        }

        tv_allincome.setText(allincome + "");
        tv_allexpend.setText(allexpend + "");


        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ExpendRecordAdapter adapter1 = new ExpendRecordAdapter(mExpendRecordList,onExpendItemClickListener);
        recyclerView1.setAdapter(adapter1);

        final String[] arry_select =new String[] {"收入","支出"};
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DetailActivity.this);
                dialog.setTitle("请选择")
                        .setSingleChoiceItems(arry_select, 0,
                                new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int which) {
                                        String select = arry_select[which];
                                        if(select.equals("收入")){
                                            RecyclerView recyclerView= (RecyclerView) findViewById(R.id.detail_rv);
                                            StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                                    StaggeredGridLayoutManager.VERTICAL);
                                            recyclerView.setLayoutManager(layoutManager1);
                                            IncomeRecordAdapter adapter = new IncomeRecordAdapter(mIncomeRecordList,onIncomeItemClickListener);
                                            recyclerView.setAdapter(adapter);
                                            tv_income.setTextColor(getResources().getColor(R.color.black));
                                            tv_expend.setTextColor(getResources().getColor(R.color.gray_8f));
                                            tv_allexpend.setTextColor(getResources().getColor(R.color.gray_8f));
                                            tv_allincome.setTextColor(getResources().getColor(R.color.black));
                                        }
                                        if(select.equals("支出")){
                                            RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.detail_rv);
                                            StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1,
                                                    StaggeredGridLayoutManager.VERTICAL);
                                            recyclerView1.setLayoutManager(layoutManager1);
                                            ExpendRecordAdapter adapter1 = new ExpendRecordAdapter(mExpendRecordList,onExpendItemClickListener);
                                            recyclerView1.setAdapter(adapter1);
                                            tv_expend.setTextColor(getResources().getColor(R.color.black));
                                            tv_income.setTextColor(getResources().getColor(R.color.gray_8f));
                                            tv_allexpend.setTextColor(getResources().getColor(R.color.black));
                                            tv_allincome.setTextColor(getResources().getColor(R.color.gray_8f));
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
        });



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