package com.example.simran.researchactivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simran on 10/4/16.
 */
public class PosterEventsRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView postedBy, description;
    TouchImageView imageView;


    public PosterEventsRecycleViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        postedBy = (TextView) itemView.findViewById(R.id.postedByText);
        description = (TextView) itemView.findViewById(R.id.descriptionLabel);
        imageView = (TouchImageView) itemView.findViewById(R.id.image);
    }




    public void onClick(View view)
    {


    }





}
