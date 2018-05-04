package com.example.nbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 强仔 on 2018/5/3.
 */

public class IncomeRecord_Adapter extends ArrayAdapter<Income_record> {
    private int resourceId;

    public IncomeRecord_Adapter(Context context, int textViewResourceID, List<Income_record> objects) {
        super(context, textViewResourceID, objects);
        resourceId = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Income_record income_record = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.type_icon = (CircleImageView) view.findViewById(R.id.type_icon);
            viewHolder.type_name = (TextView) view.findViewById(R.id.type_name);
            viewHolder.money=(TextView) view.findViewById(R.id.money);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.type_icon.setImageResource(income_record.getIconIdbyType());
        viewHolder.type_name.setText(income_record.getType());
        viewHolder.money.setText(income_record.getMoney());
        return view;
    }

    class ViewHolder {
        CircleImageView type_icon;
        TextView type_name;
        TextView money;
    }

}