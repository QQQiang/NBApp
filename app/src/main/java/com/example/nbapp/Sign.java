package com.example.nbapp;

/**
 * Created by 强仔 on 2018/5/18.
 */

public enum Sign {
    INCOME(1), EXPEND(2);

    private final int value;

    //构造方法必须是private或者默认
    private Sign(int value) {
        this.value = value;
    }

    public Sign valueOf(int value) {
        switch (value) {
            case 0:
                return Sign.INCOME;
            case 1:
                return Sign.EXPEND;
            default:
                return null;
        }
    }


}
