package com.example.nbapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 强仔 on 2018/6/3.
 */

public class Find_Adapter extends RecyclerView.Adapter<Find_Adapter.ViewHolder> {

    private List<TypeRecord> mTypeList;
    private Context mContext;

    public Find_Adapter(List<TypeRecord> mTypeList){
        this.mTypeList=mTypeList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView color;
        TextView type;

        public ViewHolder(View view){
            super(view);
            color=(TextView)view.findViewById(R.id.color);
            type=(TextView)view.findViewById(R.id.type_item);
        }
    }



    @Override
    public Find_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.find_pieview_item,parent,false);
        return new Find_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Find_Adapter.ViewHolder holder,int position){
        TypeRecord mrecord = mTypeList.get(position);
        holder.color.setBackgroundColor(mrecord.getColor());
        holder.type.setText(mrecord.getType()+"  "+mrecord.getMoney());
    }

    @Override
    public int getItemCount(){
        return mTypeList.size();
    }
}
