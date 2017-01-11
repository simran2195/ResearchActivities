package com.example.simran.researchactivities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simran on 6/26/16.
 */
public class ViewResearchNewsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2, tv3;
    ImageView imageView;

    public ViewResearchNewsRecyclerViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.title);
        tv2 = (TextView) itemView.findViewById(R.id.author);
        tv3 = (TextView) itemView.findViewById(R.id.newsAbstract);
        imageView = (ImageView) itemView.findViewById(R.id.image);

    }

    public void onClick(View view)
    {
//        Toast.makeText(view.getContext(), "Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
