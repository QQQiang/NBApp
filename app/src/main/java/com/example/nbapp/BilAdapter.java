package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 强仔 on 2018/6/2.
 */

public class BilAdapter extends RecyclerView.Adapter<BilAdapter.ViewHolder> {
    private List<MonthRecord> mMRecordList;
    private Context mContext;



    public BilAdapter(List<MonthRecord> mMRecordList){
        this.mMRecordList=mMRecordList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView month;
        TextView income;
        TextView expend;
        TextView surplus;

        public ViewHolder(View view){
            super(view);
            month=(TextView)view.findViewById(R.id.month);
            income=(TextView)view.findViewById(R.id.income);
            expend=(TextView)view.findViewById(R.id.expend);
            surplus=(TextView)view.findViewById(R.id.surplus);
        }
    }



    @Override
    public BilAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.bil_item,parent,false);
        return new BilAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BilAdapter.ViewHolder holder,int position){
        MonthRecord mrecord = mMRecordList.get(position);
        holder.month.setText(mrecord.getMonth()+"月");
        holder.income.setText(mrecord.getIncome()+"");
        holder.expend.setText(mrecord.getExpend()+"");
        holder.surplus.setText(mrecord.getSurplus()+"");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount(){
        return mMRecordList.size();
    }

}
