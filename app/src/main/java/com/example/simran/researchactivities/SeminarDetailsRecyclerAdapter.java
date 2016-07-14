package com.example.simran.researchactivities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Simran on 6/8/16.
 */
public class SeminarDetailsRecyclerAdapter extends RecyclerView.Adapter<SeminarDetailsRecyclerViewHolder>
{
    private List<SeminarItemObjects> itemList;

    Context context;
    LayoutInflater inflater;


    public SeminarDetailsRecyclerAdapter(Context context, List<SeminarItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public SeminarDetailsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.view_seminar_details, parent, false);

        SeminarDetailsRecyclerViewHolder viewHolder=new SeminarDetailsRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeminarDetailsRecyclerViewHolder holder, int position)
    {

        holder.tv1.setText(itemList.get(position).getTitle());
        holder.tv2.setText(itemList.get(position).getSpeaker());
        holder.tv3.setText(itemList.get(position).getDate());
        holder.tv4.setText(itemList.get(position).getSeminarAbstract());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SeminarDetailsRecyclerViewHolder vholder = (SeminarDetailsRecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

//            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
