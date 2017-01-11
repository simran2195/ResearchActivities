package com.example.simran.researchactivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Simran on 5/20/16.
 */
public class RecyclerAdapter extends  RecyclerView.Adapter<RecyclerViewHolder>
{

    private List<ResearchItemObjects> itemList;

    Context context;
    LayoutInflater inflater;


    public RecyclerAdapter(Context context, List<ResearchItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.research_list, parent, false);

        RecyclerViewHolder viewHolder=new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position)
    {

        holder.tv1.setText(itemList.get(position).getName());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        //holder.imageView.setImageResource(R.drawable.plusicon2);
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

            if(position == 0) {
//                Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(), Seminars.class));

            }
            if(position == 1) {
//                Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(), ResearchNews.class));

            }
            if(position == 2) {
//                Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(), PosterEventsActivity.class));

            }
            if(position == 3) {
//                Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
                v.getContext().startActivity(new Intent(v.getContext(), ResearchOpeningsActivity.class));

            }

        }
    };



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}