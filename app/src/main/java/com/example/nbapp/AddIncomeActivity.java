package com.example.nbapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AddIncomeActivity extends AppCompatActivity {


    private Button expend;
    private  Button income;
    private ImageButton close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

        income=(Button)findViewById(R.id.btn_add_income);
        close=(ImageButton)findViewById(R.id.btn_close);
        expend=(Button)findViewById(R.id.btn_add_expend);

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

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view_expendtype);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        IncomeType_IconAdapter adapter=new IncomeType_IconAdapter();
        recyclerView.setAdapter(adapter);

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
