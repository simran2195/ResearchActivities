package com.example.simran.researchactivities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Simran on 6/8/16.
 */
public class SeminarDetailsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2, tv3, tv4;
    ImageView imageView;

    public SeminarDetailsRecyclerViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.title);
        tv2 = (TextView) itemView.findViewById(R.id.speaker);
        tv3 = (TextView) itemView.findViewById(R.id.date);
        tv4 = (TextView) itemView.findViewById(R.id.seminarAbstract);
        imageView = (ImageView) itemView.findViewById(R.id.image);

    }

    public void onClick(View view)
    {
//        Toast.makeText(view.getContext(), "Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}

