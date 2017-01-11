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
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by admin on 22-Jun-16.
 */
public class MyEventsAdapter extends  RecyclerView.Adapter<MyEventsRecyclerViewHolder>
{

    private List<SeminarItemObjects> itemList;

    Context context;
    LayoutInflater inflater;


    public MyEventsAdapter(Context context, List<SeminarItemObjects> itemList)
    {
        this.context = context;
        this.itemList = itemList;
        inflater = LayoutInflater.from(context); //****
    }

    public void clear()
    {
        itemList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<SeminarItemObjects> list)
    {
//        itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyEventsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.myevents_list, parent, false);

        MyEventsRecyclerViewHolder viewHolder = new MyEventsRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyEventsRecyclerViewHolder holder, final int position)
    {

        holder.tv1.setText(itemList.get(position).getTitle());
        holder.tv2.setText(itemList.get(position).getSpeaker());
        holder.tv3.setText(itemList.get(position).getDate());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

        holder.btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                MyEventsFragment a = new MyEventsFragment();
                a.removeEvent(position);
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
//                                a.removeEvent(position);
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
//                builder.setMessage("Are you sure about deleting the event?").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();


            }
        });


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MyEventsRecyclerViewHolder vholder = (MyEventsRecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

        }
    };


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
