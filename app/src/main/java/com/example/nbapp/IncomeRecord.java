package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class IncomeRecord extends DataSupport {
    private int income_id;
    private int income_money;
    private String income_type;
    private Date income_time;
    private int income_type_iconID;

    public int getId(){
        return income_id;
    }
    public void setId(){
        Random random=new Random();
        income_id=+random.nextInt(100000);
    }

    public int getMoney(){
        return income_money;
    }
    public void setName(int income_money){
        this.income_money=income_money;
    }

    public String getType(){
        return income_type;
    }
    public void setType(String income_type){
        this.income_type=income_type;
    }

    public Date getTime(){
        return income_time;
    }
    public void setTime(Date income_time){
        this.income_time=income_time;
    }

    public int getIconIdbyType(){
        List<IncomeType_Icon> icon_ids=DataSupport.findAll(IncomeType_Icon.class);

        for(IncomeType_Icon icon_id:icon_ids){
            if(icon_id.getType().equals(this.income_type)){
                return icon_id.getIconid();
            }
        }
        return -1;
    }

}