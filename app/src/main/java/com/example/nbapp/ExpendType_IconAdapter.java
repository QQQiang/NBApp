package com.example.nbapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private String TAG="Expend_TypeIconAdpter";

    private List<ExpendType_Icon> mExpendTypeList;
    private Context mContext;
    private ViewHolder temp=null;

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener=null;

    public ExpendType_IconAdapter(List<ExpendType_Icon> mExpendTypeList,
                                  OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener){
        this.mExpendTypeList= mExpendTypeList;
        this.mOnRecyclerviewItemClickListener=mOnRecyclerviewItemClickListener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View type_view;
        ImageView type_icon;
        TextView type_name;

        public ViewHolder(View view) {
            super(view);
            type_icon = (ImageView) view.findViewById(R.id.add_type_icon);
            type_name = (TextView) view.findViewById(R.id.add_type_name);
        }

    }



    @Override
    public ExpendType_IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Log.i(TAG, "onCreateViewHolder: ");

        if(mContext ==null){
            mContext=parent.getContext();
        }

        View view= LayoutInflater.from(mContext).inflate(R.layout.add_type_item,parent,false);
        final ViewHolder holder =new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将监听传递给自定义接口
                if(temp!=null){
                    temp.type_icon.setBackgroundResource(R.color.whitesmoke);
                }
                holder.type_icon.setBackgroundResource(R.color.sandybrown);
                temp=holder;
                mOnRecyclerviewItemClickListener.onItemClickListener(v, ((int) v.getTag()));
            }
        });

        return new ExpendType_IconAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpendType_IconAdapter.ViewHolder holder,int position){

        Log.i(TAG, "onBindViewHolder: ");
        ExpendType_Icon expendType_icon = mExpendTypeList.get(position);
        holder.type_name.setText(expendType_icon.getType());
        holder.type_icon.setImageResource(expendType_icon.getIconid());
        holder.itemView.setTag(position);
        }

    @Override
    public int getItemCount(){
        return mExpendTypeList.size();
    }


}





