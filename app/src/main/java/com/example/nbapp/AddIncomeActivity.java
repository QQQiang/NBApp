package com.example.nbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddIncomeActivity extends AppCompatActivity {
    private IncomeType_Icon salary=new IncomeType_Icon();
    private IncomeType_Icon partjob=new IncomeType_Icon();
    private IncomeType_Icon investin=new IncomeType_Icon();
    private IncomeType_Icon redpacket=new IncomeType_Icon();
    private IncomeType_Icon other=new IncomeType_Icon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_income);

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




    }
}
