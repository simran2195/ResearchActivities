package com.example.simran.researchactivities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 17-Sep-16.
 */
public class DirectoryRecyclerAdapter extends RecyclerView.Adapter<DirectoryRecyclerViewHolder>
{
    private List<DirectoryItemObjects> itemList;

//    AdapterView.OnItemClickListener mItemClickListener;

    Context context;
    LayoutInflater inflater;

    public void clear()
    {
        itemList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<DirectoryItemObjects> list)
    {
//        itemList.addAll(list);
        notifyDataSetChanged();
    }


    public DirectoryRecyclerAdapter(Context context, List<DirectoryItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public DirectoryRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.directory_list, parent, false);

        DirectoryRecyclerViewHolder viewHolder=new DirectoryRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DirectoryRecyclerViewHolder holder, final int position)
    {
        holder.tv1.setText(itemList.get(position).getName());
        holder.tv2.setText(itemList.get(position).getDesignation());
        holder.tv3.setText(itemList.get(position).getExtension());
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
//                                try {
//                                    a.addEvent(position);
//                                } catch (Throwable throwable) {
//                                    throwable.printStackTrace();
//                                }
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

            DirectoryRecyclerViewHolder vholder = (DirectoryRecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

//            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };

    public void addToCalendar(View view, int position)
    {

        //******************************************************
        // Added here...


        //Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        Directory s = new Directory();
        List<DirectoryItemObjects> allItems = s.getAllItemList();
        DirectoryItemObjects obj = allItems.get(position);
//        String dateString = obj.getDateToAddToCalendar();
//        //String date = "2016,5,15";
//        String[] stringDate = dateString.split(",");
//        int year = Integer.valueOf(stringDate[0]);
//        int month = Integer.valueOf(stringDate[1]);
//        int date = Integer.valueOf(stringDate[2]);
//
//        String title = obj.getTitle();
//
//
//        Intent calIntent = new Intent(Intent.ACTION_INSERT);
//        calIntent.setType("vnd.android.cursor.item/event");
//        calIntent.putExtra(CalendarContract.Events.TITLE, title);
//        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "IIIT Delhi");
//        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Description of seminar...");
//
//
//
////        Calendar cal = new GregorianCalendar(2016, 6, 15); // Date
//        //Calendar cal = new GregorianCalendar(Integer.parseInt(stringDate[0]), Integer.parseInt(stringDate[1]), Integer.parseInt(stringDate[2])); // Date
//        Calendar cal = new GregorianCalendar(year, month, date);
//        cal.set(Calendar.HOUR, 16); //Start Time
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//
//        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
//                cal.getTimeInMillis());
//        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
//                cal.getTimeInMillis()+ 60 * 60 * 1000);
//        calIntent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE); // Remove if adding friends
//
//        view.getContext().startActivity(calIntent);

    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
