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
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private String TAG="DetailActivity";

    private ImageButton detail;
    private ImageButton add;
    private TextView allincome;
    private TextView allexpend;

    private double allincomemoney;
    private double allexpendmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Log.i(TAG, "onCreate: ");

        add=(ImageButton)findViewById(R.id.btn_menu_add);
        detail=(ImageButton)findViewById(R.id.btn_menu_detail);
        detail.setImageResource(R.drawable.detail);
        //allincome.setText();
       // allincome.setText();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,AddExpendActivity.class);
                startActivity(intent);
            }
        });

    }
    
    @Override
    protected void onStart(){
        super.onStart();
        allexpend=(TextView)findViewById(R.id.detail_allexpend);
        allincome=(TextView)findViewById(R.id.detail_allincome);

        RecyclerView recyclerViewincome = (RecyclerView) findViewById(R.id.recycler_view_detail);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerViewincome.setLayoutManager(layoutManager1);
        IncomeRecordAdapter adapter1 = new IncomeRecordAdapter();
        recyclerViewincome.setAdapter(adapter1);

        RecyclerView recyclerViewexpend = (RecyclerView) findViewById(R.id.recycler_view_detail);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerViewexpend.setLayoutManager(layoutManager2);
        IncomeRecordAdapter adapter2 = new IncomeRecordAdapter();
        recyclerViewexpend.setAdapter(adapter2);


    }
    
    @Override
    protected  void onStop(){
        super.onStop();
        Log.i(TAG, "onStop: ");
    }
}
