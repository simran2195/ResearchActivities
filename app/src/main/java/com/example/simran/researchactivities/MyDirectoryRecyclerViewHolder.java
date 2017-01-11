package com.example.simran.researchactivities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simran on 8/30/16.
 */
public class MyDirectoryRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView face;
    ImageButton call, emailsign;


    public MyDirectoryRecyclerViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.name);
        tv2 = (TextView) itemView.findViewById(R.id.designation);
        tv3 = (TextView) itemView.findViewById(R.id.extension);
        tv4 = (TextView) itemView.findViewById(R.id.room);
        tv5 = (TextView) itemView.findViewById(R.id.email);
        //face = (TextView) itemView.findViewById(R.id.face);
        call = (ImageButton) itemView.findViewById(R.id.call);
        emailsign = (ImageButton) itemView.findViewById(R.id.emailsign);
        //btn2 = (ImageButton) itemView.findViewById(R.id.removeButton);
    }

    public void bind(DirectoryItemObjects directoryItemObjects)
    {
        tv1.setText(directoryItemObjects.getName());
        tv2.setText(directoryItemObjects.getDesignation());
        tv3.setText(directoryItemObjects.getExtension());
        tv4.setText(directoryItemObjects.getRoom());
        tv5.setText(directoryItemObjects.getEmail());
    }


    public void onClick(View view)
    {
        int pos = getPosition();
//        Toast.makeText(view.getContext(), "Clicked **" + pos, Toast.LENGTH_SHORT).show();

    }

}
