package com.example.simran.researchactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Simran on 6/26/16.
 */
public class ResearchNewsRecyclerAdapter extends RecyclerView.Adapter<ResearchNewsViewHolder>
{

    private List<ResearchNewsItemObjects> itemList;

//    AdapterView.OnItemClickListener mItemClickListener;

    Context context;
    LayoutInflater inflater;

    public void clear()
    {
        itemList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<ResearchNewsItemObjects> list)
    {
//        itemList.addAll(list);
        notifyDataSetChanged();
    }


    public ResearchNewsRecyclerAdapter(Context context, List<ResearchNewsItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }

    @Override
    public ResearchNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.research_news_list, parent, false);

        ResearchNewsViewHolder viewHolder=new ResearchNewsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResearchNewsViewHolder holder, final int position)
    {

        holder.tv1.setText(itemList.get(position).getNewsTitle());
        holder.tv2.setText(itemList.get(position).getAuthors());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

//        holder.btn.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//
//                final View v = view;
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        switch (which)
//                        {
//                            case DialogInterface.BUTTON_POSITIVE:
//                                //Yes button clicked
//                                MyEventsFragment a = new MyEventsFragment();
//                                a.addEvent(position);
//                                addToCalendar(v, position);
//                                break;
//
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                //No button clicked
//                                break;
//                        }
//                    }
//                };
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Add Seminar to Calendar?").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
//
//
//            }
//        });
    }

    View.OnClickListener clickListener=new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            ResearchNewsViewHolder vholder = (ResearchNewsViewHolder) v.getTag();
            int position = vholder.getPosition();

//            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };





    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

}
