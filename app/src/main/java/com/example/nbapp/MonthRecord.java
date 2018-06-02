package com.example.nbapp;

import org.litepal.crud.DataSupport;

/**
 * Created by 强仔 on 2018/6/2.
 */

public class MonthRecord extends DataSupport{
    private int year;
    private int month;
    private double expend;
    private double income;
    private double surplus;

    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){
        return year;
    }
    public void setMonth(int month){
        this.month=month;
    }
    public int getMonth(){
        return month;
    }

    public double getExpend(){
       return expend;
    }

    public void setExpend(double expend){
        this.expend=expend;
    }

    public void setIncome(double income){
        this.income=income;
    }

    public double getIncome(){
        return income;
    }

    public void setSurplus(double surplus){
        this.surplus=surplus;
    }

    public double getSurplus(){
        return surplus;
    }
}

