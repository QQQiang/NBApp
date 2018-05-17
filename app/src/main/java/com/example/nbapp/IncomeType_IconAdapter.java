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
    private IncomeType_IconAdapter.ViewHolder temp=null;

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener =null;

    public IncomeType_IconAdapter(List<IncomeType_Icon> mIncomeTypeList,
                                  OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener){
        this.mIncomeTypeList= mIncomeTypeList;
        this.mOnRecyclerviewItemClickListener=mOnRecyclerviewItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView type_icon;
        TextView type_name;

        public ViewHolder(View view){
            super(view);
            type_icon=(ImageView)view.findViewById(R.id.add_type_icon);
            type_name=(TextView)view.findViewById(R.id.add_type_name);
        }
    }



    @Override
    public IncomeType_IconAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
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

        return new IncomeType_IconAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomeType_IconAdapter.ViewHolder holder,int position){
        IncomeType_Icon incomeType_icon = mIncomeTypeList.get(position);
        holder.type_name.setText(incomeType_icon.getType());
        holder.type_icon.setImageResource(incomeType_icon.getIconid());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount(){
        return mIncomeTypeList.size();
    }


}
