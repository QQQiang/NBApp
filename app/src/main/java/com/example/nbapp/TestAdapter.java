package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 强仔 on 2018/5/14.
 */

public class TestAdapter extends ArrayAdapter<IncomeRecord> {
    private String TAG="ExpendRecordAdapter";
    private int resourceId;

    public TestAdapter(Context context,int textViewResourceId,List<IncomeRecord> objects){
       super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        IncomeRecord record=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView detail_money=(TextView)view.findViewById(R.id.detail_money);
        TextView detail_type_name=(TextView)view.findViewById(R.id.detail_type_name);
        ImageView detail_type_icon=(ImageView)view.findViewById(R.id.detail_type_icon);

        detail_money.setText(record.getMoney()+"");
        detail_type_name.setText(record.getType());
        detail_type_icon.setImageResource(record.getIconid());
        return view;
    }

}
