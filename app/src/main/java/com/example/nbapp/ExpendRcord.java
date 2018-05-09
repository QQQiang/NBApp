package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class ExpendRcord extends DataSupport {
    private int expend_id;
    private int expend_money;
    private String expend_type;
    private Date expend_time;
    private int income_type_iconID;

    public int getId(){
        return expend_id;
    }
    public void setId(){
        Random random=new Random();
        expend_id=+random.nextInt(100000);
    }

    public int getMoney(){
        return expend_money;
    }
    public void setName(int expend_money){
        this.expend_money=expend_money;
    }

    public String getType(){
        return expend_type;
    }
    public void setType(String expend_type){
        this.expend_type=expend_type;
    }

    public Date getTime(){
        return expend_time;
    }
    public void setTime(Date expend_time){
        this.expend_time=expend_time;
    }

    public int getIconIdbyType(){
        List<ExpendType_Icon> icon_ids=DataSupport.findAll(ExpendType_Icon.class);

        for(ExpendType_Icon icon_id:icon_ids){
            if(icon_id.getType().equals(this.expend_type)){
                return icon_id.getgetIconid();
            }
        }
        return -1;
    }


}
