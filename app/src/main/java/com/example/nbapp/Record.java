package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Random;

/**
 * Created by 强仔 on 2018/5/15.
 */

public class Record extends DataSupport{
    private int income_id;
    private double income_money;
    private String income_type;
    private String income_date;
    private int income_iconid;

    public int getId(){
        return income_id;
    }
    public void setId(){
        Random random=new Random();
        income_id=+random.nextInt(100000);
    }

    public double getMoney(){
        return income_money;
    }
    public void setMoney(double income_money){
        this.income_money=income_money;
    }

    public String getType(){
        return income_type;
    }
    public void setType(String income_type){
        this.income_type=income_type;
    }

    public String getDate(){
        return income_date;
    }
    public void setDate(String income_date){
        this.income_date=income_date;
    }

    public int getIconid(){
        return income_iconid;
    }
    public void setIconid(int expend_iconid){
        this.income_iconid=expend_iconid;
    }
}
