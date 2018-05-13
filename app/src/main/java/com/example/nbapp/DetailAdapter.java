package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 强仔 on 2018/5/13.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private List<ExpendRecord> mRecordList;
    private Context mContext;
    private String WeekNames[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    public DetailAdapter(List<ExpendRecord> mRecordList){
        this.mRecordList= mRecordList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView detail_date;
        TextView detail_week;
        TextView detail_money;

        public ViewHolder(View view){
            super(view);
            detail_date=(TextView)view.findViewById(R.id.detail_timeBar_date);
            detail_week=(TextView)view.findViewById(R.id.detail_timeBar_week);
            detail_money=(TextView)view.findViewById(R.id.detail_money);
        }
    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.add_type_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder,int position){
        ExpendRecord expendrecord = mRecordList.get(position);

        Calendar calender = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calender.setTime(sdf.parse(expendrecord.getDate()));
            holder.detail_date.setText(calender.get(Calendar.MONTH)+"月"+calender.get(Calendar.DATE)+"号");
            holder.detail_week.setText(WeekNames[calender.get(Calendar.DAY_OF_WEEK)-1]);
        } catch (ParseException e) {
            Log.d("DerailAdapter", "onBindViewHolder: "+e.getMessage());
        }

        holder.detail_money.setText("-"+expendrecord.getMoney());

    }

    @Override
    public int getItemCount(){
        return mRecordList.size();
    }

}
