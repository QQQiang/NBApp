package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
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

    private List<Record> mRecordList;
    private Context mContext;
    private String WeekNames[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener=null;

    public DetailAdapter(List<Record> mRecordList,
                         OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener){
        this.mRecordList=mRecordList;
        this.mOnRecyclerviewItemClickListener=mOnRecyclerviewItemClickListener;

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView detail_date;
        TextView detail_week;
        TextView detail_money;
        ImageView detail_type_icon;
        TextView detail_type_name;

        public ViewHolder(View view){
            super(view);
            detail_date=(TextView)view.findViewById(R.id.detail_timeBar_date);
            detail_week=(TextView)view.findViewById(R.id.detail_timeBar_week);
            detail_money=(TextView)view.findViewById(R.id.detail_money);
            detail_type_icon=(ImageView) view.findViewById(R.id.detail_type_icon);
            detail_type_name=(TextView)view.findViewById(R.id.detail_type_name);
        }
    }



    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.detail_record_item,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerviewItemClickListener.onItemClickListener(v, ((int) v.getTag()));
            }
        });
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailAdapter.ViewHolder holder,int position){
       Record record = mRecordList.get(position);
        holder.detail_type_name.setText(record.getType());

        Calendar calender = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calender.setTime(sdf.parse(record.getDate()));
            holder.detail_date.setText((calender.get(Calendar.MONTH)+1)+"月"+calender.get(Calendar.DATE)+"号");
            holder.detail_week.setText(WeekNames[calender.get(Calendar.DAY_OF_WEEK)-1]);
        } catch (ParseException e) {
            Log.d("DerailAdapter", "onBindViewHolder: "+e.getMessage());
        }
        switch (record.getSign()){
            case 1:
                holder.detail_money.setText(""+record.getMoney());
                break;
            case 2:
                holder.detail_money.setText("-"+record.getMoney());
                break;
            default:
                break;
        }
        holder.detail_type_name.setText(record.getType());
        holder.detail_type_icon.setImageResource(record.getIconid());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount(){
        return mRecordList.size();
    }

}
