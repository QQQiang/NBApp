package com.example.nbapp;

import org.litepal.crud.DataSupport;


/**
 * Created by 强仔 on 2018/5/17.
 */

public class Budget extends DataSupport {
    private String account;
    private int year;
    private int month;

    public String getAccount(){
        return account;
    }

    public int getYear(){ return year;}

    public int getMonth(){return month;}

    public void setAccount(String account){
        this.account=account;
    }
    public void  setYear(int year){
        this.year=year;
    }
    public void setMonth(int month){
        this.month=month;
    }
}
