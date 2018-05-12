package com.example.nbapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 强仔 on 2018/5/12.
 */

public class ExpendType_IconAdapter extends RecyclerView.Adapter<ExpendType_IconAdapter.ViewHolder>{
    private List<ExpendType_Icon> mExpendTypeList;

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

    public ExpendType_IconAdapter(){
        mExpendTypeList= DataSupport.findAll(ExpendType_Icon.class);
    }

    @Override
    public ExpendType_IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext ==null){
            mContext=parent.getContext();
        }

        View view= LayoutInflater.from(mContext).inflate(R.layout.add_type_item,parent,false);
        return new ExpendType_IconAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpendType_IconAdapter.ViewHolder holder,int position){
        ExpendType_Icon expendType_icon = mExpendTypeList.get(position);
        holder.type_name.setText(expendType_icon.getType());
        holder.type_icon.setImageResource(expendType_icon.getIconid());
    }

    @Override
    public int getItemCount(){
        return mExpendTypeList.size();
    }

}
