package com.example.news;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<news> {

    private ArrayList<news> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName; //heading
        TextView txtType; //descryption

        ImageView info; // image
        Button button;
    }

    public CustomAdapter(ArrayList<news> data, Context context) {
        super(context, R.layout.custom_list, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        news dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_list, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.heading);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.description);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.button=(Button)convertView.findViewById(R.id.share);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;


        viewHolder.txtName.setText(dataModel.getHeading());
        viewHolder.txtType.setText(dataModel.getDescryption());

        Glide.with(this.mContext).load(dataModel.getUrl()).into(viewHolder.info);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sendIntent=new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,dataModel.getReference());
                sendIntent.setType("text/plain");
                Intent sintent=Intent.createChooser(sendIntent,null);
                sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(sintent);

            }
        });



        return convertView;
    }
}
