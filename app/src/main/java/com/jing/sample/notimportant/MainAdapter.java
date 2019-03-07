package com.jing.sample.notimportant;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jing.sample.R;

import java.util.List;


public class MainAdapter extends ArrayAdapter<ItemSample> {

    private Typeface mTypeFaceLight;
    private Typeface mTypeFaceRegular;

    public MainAdapter(Context context, List<ItemSample> objects) {
        super(context, 0, objects);

        mTypeFaceLight = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
        mTypeFaceRegular = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemSample c = getItem(position);

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, null);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvDesc = convertView.findViewById(R.id.tvDesc);
            holder.tvNew = convertView.findViewById(R.id.tvNew);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNew.setTypeface(mTypeFaceRegular);
        holder.tvName.setTypeface(mTypeFaceRegular);
        holder.tvDesc.setTypeface(mTypeFaceLight);

        holder.tvName.setText(c.mTitle);
        holder.tvDesc.setText(c.mDesc);

        if (c.mIsNew)
            holder.tvNew.setVisibility(View.VISIBLE);
        else
            holder.tvNew.setVisibility(View.GONE);

        return convertView;
    }

    private class ViewHolder {

        TextView tvName, tvDesc;
        TextView tvNew;
    }
}
