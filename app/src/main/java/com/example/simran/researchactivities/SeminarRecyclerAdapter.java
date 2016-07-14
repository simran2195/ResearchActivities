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
 * Created by Simran on 6/7/16.
 */
public class SeminarRecyclerAdapter extends RecyclerView.Adapter<SeminarRecycleViewHolder>
{
    private List<SeminarItemObjects> itemList;

//    AdapterView.OnItemClickListener mItemClickListener;

    Context context;
    LayoutInflater inflater;

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


    public SeminarRecyclerAdapter(Context context, List<SeminarItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public SeminarRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.seminar_list, parent, false);

        SeminarRecycleViewHolder viewHolder=new SeminarRecycleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SeminarRecycleViewHolder holder, final int position)
    {

        holder.tv1.setText(itemList.get(position).getTitle());
        holder.tv2.setText(itemList.get(position).getSpeaker());
        holder.tv3.setText(itemList.get(position).getDate());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

        holder.btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                final View v = view;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                             case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                MyEventsFragment a = new MyEventsFragment();
                                a.addEvent(position);
                                addToCalendar(v, position);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Add Seminar to Calendar?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


            }
        });
    }

    View.OnClickListener clickListener=new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            SeminarRecycleViewHolder vholder = (SeminarRecycleViewHolder) v.getTag();
            int position = vholder.getPosition();

//            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };

    public void addToCalendar(View view, int position)
    {

        //******************************************************
        // Added here...


        //Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        Seminars s = new Seminars();
        List<SeminarItemObjects> allItems = s.getAllItemList();
        SeminarItemObjects obj = allItems.get(position);
        String dateString = obj.getDateToAddToCalendar();
        //String date = "2016,5,15";
        String[] stringDate = dateString.split(",");
        int year = Integer.valueOf(stringDate[0]);
        int month = Integer.valueOf(stringDate[1]);
        int date = Integer.valueOf(stringDate[2]);

        String title = obj.getTitle();


        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, title);
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "IIIT Delhi");
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Description of seminar...");



//        Calendar cal = new GregorianCalendar(2016, 6, 15); // Date
        //Calendar cal = new GregorianCalendar(Integer.parseInt(stringDate[0]), Integer.parseInt(stringDate[1]), Integer.parseInt(stringDate[2])); // Date
        Calendar cal = new GregorianCalendar(year, month, date);
        cal.set(Calendar.HOUR, 16); //Start Time
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                cal.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                cal.getTimeInMillis()+ 60 * 60 * 1000);
        calIntent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE); // Remove if adding friends

        view.getContext().startActivity(calIntent);

    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
