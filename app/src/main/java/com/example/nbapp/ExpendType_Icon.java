package com.example.nbapp;

import org.litepal.crud.DataSupport;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class ExpendType_Icon extends DataSupport {
    private String expend_type;
    private int iconid;

    public int getIconid(){
        return iconid;
    }

    public void setIconid(int inconid){this.iconid=inconid;}

    public String getType(){return expend_type;
    }
    public void setType(String expend_type){
        this.expend_type=expend_type;
    }
}
