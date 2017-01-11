package com.example.simran.researchactivities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 17-Sep-16.
 */
public class DirectoryRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView imageView;
    ImageButton btn2;


    public DirectoryRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        tv1 = (TextView) itemView.findViewById(R.id.name);
        tv2 = (TextView) itemView.findViewById(R.id.designation);
        tv3 = (TextView) itemView.findViewById(R.id.extension);
        tv2 = (TextView) itemView.findViewById(R.id.room);
        tv3 = (TextView) itemView.findViewById(R.id.email);
        imageView = (ImageView) itemView.findViewById(R.id.image);
        btn2 = (ImageButton) itemView.findViewById(R.id.removeButton);
    }

    public void onClick(View view) {
        int pos = getPosition();
//        Toast.makeText(view.getContext(), "Clicked **" + pos, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(view.getContext(), ViewSeminarDetails.class);
        i.putExtra("value", pos);
        view.getContext().startActivity(i);

    }
}