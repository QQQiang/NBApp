package com.example.nbapp;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 强仔 on 2018/6/2.
 */

public class GlobalVariabls {
    final static public List<IncomeType_Icon> mIncomeTypeList = DataSupport.findAll(IncomeType_Icon.class);
    final static public List<ExpendType_Icon> mExpendTypeList = DataSupport.findAll(ExpendType_Icon.class);
}

