package com.example.nbapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.litepal.LitePalApplication.getContext;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class IncomeRecord_Adapter extends RecyclerView.Adapter<IncomeRecord_Adapter.ViewHolder> {
    private Context mContext;
    private List<IncomeRecord> mIncomeRecordList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView type_icon;
        TextView type_name;
        TextView money;

        public ViewHolder(View view){
            super(view);
            type_icon=(CircleImageView)view.findViewById(R.id.detail_type_icon);
            type_name=(TextView)view.findViewById(R.id.detail_money);
            money=(TextView)view.findViewById(R.id.detail_money);
        }
    }

    public IncomeRecord_Adapter(){
        mIncomeRecordList= DataSupport.findAll(IncomeRecord.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }

        View view=LayoutInflater.from(mContext).inflate(R.layout.detail_record_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        IncomeRecord incomeRecord=mIncomeRecordList.get(position);
        holder.type_name.setText(incomeRecord.getType());



    }

    @Override
    public int getItemCount(){
        return mIncomeRecordList.size();
    }

}