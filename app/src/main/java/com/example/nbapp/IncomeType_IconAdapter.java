package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 强仔 on 2018/5/12.
 */

public class IncomeType_IconAdapter extends RecyclerView.Adapter<IncomeType_IconAdapter.ViewHolder>{
    private List<IncomeType_Icon> mIncomeTypeList;

    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView type_icon;
        TextView type_name;

        public ViewHolder(View view){
            super(view);
            type_icon=(ImageView)view.findViewById(R.id.add_type_icon);
            type_name=(TextView)view.findViewById(R.id.add_type_name);
        }
    }

    public IncomeType_IconAdapter(){
        mIncomeTypeList= DataSupport.findAll(IncomeType_Icon.class);
    }

    @Override
    public IncomeType_IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }

        View view= LayoutInflater.from(mContext).inflate(R.layout.add_type_item,parent,false);
        return new IncomeType_IconAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomeType_IconAdapter.ViewHolder holder,int position){
        IncomeType_Icon incomeType_icon = mIncomeTypeList.get(position);
        holder.type_name.setText(incomeType_icon.getType());
        holder.type_icon.setImageResource(incomeType_icon.getIconid());

    }

    @Override
    public int getItemCount(){
        return mIncomeTypeList.size();
    }

}