package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class ExpendRecord extends DataSupport {
    private int expend_id;
    private double expend_money;
    private String expend_type;
    private String expend_date;
    private int expend_iconid;


    public int getId(){
        return expend_id;
    }
    public void setId(){
        Random random=new Random();
        expend_id=+random.nextInt(100000);
    }

    public double getMoney(){
        return expend_money;
    }
    public void setMoney(double expend_money){
        this.expend_money=expend_money;
    }

    public String getType(){
        return expend_type;
    }
    public void setType(String expend_type){
        this.expend_type=expend_type;
    }

    public int getIconid(){
        return expend_iconid;
    }
    public void setIconid(int expend_iconid){
        this.expend_iconid=expend_iconid;
    }

    public String getDate(){
        return expend_date;
    }
    public void setDate(String expend_date){
        this.expend_date=expend_date;
    }

}
