package com.example.simran.researchactivities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Simran on 6/7/16.
 */
public class SeminarRecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2, tv3;
    ImageView imageView;
    ImageButton btn;


    public SeminarRecycleViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.title);
        tv2 = (TextView) itemView.findViewById(R.id.speaker);
        tv3 = (TextView) itemView.findViewById(R.id.date);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        btn = (ImageButton) itemView.findViewById(R.id.imageButton);
    }




    public void onClick(View view)
    {
        int pos = getPosition();
//        Toast.makeText(view.getContext(), "Clicked **" + pos, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(view.getContext(), ViewSeminarDetails.class);
        i.putExtra("value", pos);
        view.getContext().startActivity(i);

    }




}
