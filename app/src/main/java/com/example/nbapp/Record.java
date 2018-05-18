package com.example.nbapp;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static com.example.nbapp.R.drawable.calender;

/**
 * Created by 强仔 on 2018/5/15.
 */

public class Record extends DataSupport{
    private int sign;//1标识income，2标识expend
    private double money;
    private String type;
    private String date;
    private int iconid;
    private  int year;
    private int month;

    public int getYear(){
        return year;
    }


    public void setSign(int sign){
        if(sign==2 || sign==1){
            this.sign=sign;
        }
        else {
            Log.d("Record", "setSign: "+"sign illegal!");
        }
    }

    public int getSign(){
        return sign;
    }



    public double getMoney(){
        return money;
    }
    public void setMoney(double money){
        this.money=money;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
        Calendar calender = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calender.setTime(sdf.parse(date));

        }catch (ParseException e) {
            Log.d("Record", "onBindViewHolder: "+e.getMessage());
        }
        this.month= calender.get(Calendar.MONTH);
        this.year=calender.get(Calendar.YEAR);
    }

    public int getIconid(){
        return iconid;
    }
    public void setIconid(int iconid){
        this.iconid=iconid;
    }


}
