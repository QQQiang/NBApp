package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by 强仔 on 2018/5/6.
 */

public class RecordTime extends DataSupport {
    private String date;
    private String week;
    private double allincome;
    private double allexpend;

    public void setDate(Date date){
    }

    public void setWeek(){

    }

    public void setAllincome(double allincome){
        this.allincome=allincome;
    }

    public void setAllexpend(double allexpend){
        this.allexpend=allexpend;
    }

    public String getDate(){
        return date;
    }

    public String getWeek(){
        return week;
    }

    public double getAllincome(){
        return allincome;
    }

    public double getAllexpend(){
        return allexpend;
    }
}
