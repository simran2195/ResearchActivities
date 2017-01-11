package com.example.simran.researchactivities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simran on 6/26/16.
 */
public class ResearchNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView tv1, tv2;
    ImageView imageView;

    public ResearchNewsViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.title);
        tv2 = (TextView) itemView.findViewById(R.id.authors);
        imageView = (ImageView) itemView.findViewById(R.id.image);

    }

    public void onClick(View view)
    {
        int pos = getPosition();
        Intent i = new Intent(view.getContext(), ViewResearchNewsDetails.class);
        i.putExtra("value", pos);
        view.getContext().startActivity(i);

    }
}
