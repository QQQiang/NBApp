package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Random;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class IncomeType_Icon extends DataSupport {
    private String income_type;
    private int iconid;

    public int getIconid(){
        return iconid;
    }

    public String getType(){return income_type;
    }
    public void setType(String income_type){
        this.income_type=income_type;
    }
}
