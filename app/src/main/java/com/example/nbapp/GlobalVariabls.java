package com.example.nbapp;

import android.graphics.Color;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;

/**
 * Created by 强仔 on 2018/6/2.
 */

public class GlobalVariabls {
    final static public List<IncomeType_Icon> mIncomeTypeList = DataSupport.findAll(IncomeType_Icon.class);
    final static public List<ExpendType_Icon> mExpendTypeList = DataSupport.findAll(ExpendType_Icon.class);

    final static public String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

   final static public String getSysMonth() {
        Calendar date = Calendar.getInstance();
        String month = String.valueOf(date.get(Calendar.MONTH)+1);
        return month;
    }

    final static public String getSysDate(){
        Calendar date = Calendar.getInstance();
        String day1 = String.valueOf(date.get(Calendar.DATE));
        return day1;
    }



    public static final int BLACK = 0xFF000000;
    public static final int DKGRAY = 0xFF444444;
    public static final int GRAY = 0xFF888888;
    public static final int LTGRAY = 0xFFCCCCCC;
    public static final int WHITE = 0xFFFFFFFF;
    public static final int RED = 0xFFFF0000;
    public static final int GREEN = 0xFF00FF00;
    public static final int BLUE = 0xFF0000FF;
    public static final int YELLOW = 0xFFFFFF00;
    public static final int CYAN = 0xFF00FFFF;
    public static final int MAGENTA = 0xFFFF00FF;
    public static final int TRANSPARENT = 0;

}

