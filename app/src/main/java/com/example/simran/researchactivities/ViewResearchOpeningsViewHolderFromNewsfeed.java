package com.example.simran.researchactivities;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by admin on 18-Jul-16.
 */
public class ViewResearchOpeningsViewHolderFromNewsfeed extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView tv1, tv2, tv3;
    ImageView imageView;
    //ImageButton btn2;

    public ViewResearchOpeningsViewHolderFromNewsfeed(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.title);
        tv2 = (TextView) itemView.findViewById(R.id.speaker);
        tv3 = (TextView) itemView.findViewById(R.id.date);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        //btn2 = (ImageButton) itemView.findViewById(R.id.removeButton);
    }

    public void onClick(View view)
    {
        int pos = getPosition();
        //Toast.makeText(view.getContext(), "Clicked **" + pos, Toast.LENGTH_SHORT).show();
        String c = getClass().toString();
        System.out.println("Class ----->" + c);
        Intent intent = new Intent(view.getContext(),ViewResearchOpeningsDetailsFromNewsfeed.class);
        intent.putExtra("value", pos);
        view.getContext().startActivity(intent);

    }
}
//class com.example.simran.researchactivities.ViewResearchOpeningsViewHolder
//