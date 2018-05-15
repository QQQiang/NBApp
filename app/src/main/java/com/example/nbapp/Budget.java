package com.example.nbapp;

import org.litepal.crud.DataSupport;

/**
 * Created by 强仔 on 2018/5/4.
 */

public class Budget extends DataSupport {
    private int year;
    private int mouth;
    private double money;

    public void setYear(int year){
        this.year=year;
    }

    public void setMouth(int mouth){
        this.mouth=mouth;
    }

    public void setMoney(double year){
        this.money=money;
    }

    public int getYear(){
        return year;
    }

    public int getMouth(){
        return mouth;
    }

    public double getMoney(){
        return money;
    }
}
