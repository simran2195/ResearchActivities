package com.example.simran.researchactivities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Simran on 5/20/16.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2;
    ImageView imageView;

    public RecyclerViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.list_title);
        tv2 = (TextView) itemView.findViewById(R.id.list_desc);
        imageView = (ImageView) itemView.findViewById(R.id.list_avatar);

    }

    public void onClick(View view)
    {
//        Toast.makeText(view.getContext(), "Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, Seminars.class);
//        startActivity(intent);

    }
}