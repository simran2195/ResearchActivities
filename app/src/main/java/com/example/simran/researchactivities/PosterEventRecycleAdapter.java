package com.example.simran.researchactivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Simran on 10/4/16.
 */
public class PosterEventRecycleAdapter  extends RecyclerView.Adapter<PosterEventsRecycleViewHolder>
{

    private List<PosterEventObject> itemList;

//    AdapterView.OnItemClickListener mItemClickListener;

    Context context;
    LayoutInflater inflater;

    public void clear()
    {
        itemList.clear();
        notifyDataSetChanged();
    }
    public PosterEventRecycleAdapter(Context context, List<PosterEventObject> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public PosterEventsRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.poster_event_card, parent, false);

        PosterEventsRecycleViewHolder viewHolder=new PosterEventsRecycleViewHolder(v);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final PosterEventsRecycleViewHolder holder, final int position)
    {

        holder.postedBy.setText(itemList.get(position).getPostedBy());
        holder.description.setText(itemList.get(position).getDescription());
        if(itemList.get(position).getImguri()!= null)
        {

            holder.imageView.setImageURI(itemList.get(position).getImguri());
        }
        else {
            holder.imageView.setImageResource(itemList.get(position).getImage());
        }
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);


        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

        holder.imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

//
                final View v = view;

                ImageView tempImageView = holder.imageView;
                AlertDialog.Builder imageDialog = new AlertDialog.Builder(context);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


                View layout = inflater.inflate(R.layout.custom_fullimage_dialog, (ViewGroup) view.findViewById(R.id.layout_root));
                TouchImageView image = (TouchImageView) layout.findViewById(R.id.fullimage);
                image.setImageDrawable(tempImageView.getDrawable());
                imageDialog.setView(layout);
                imageDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });


                imageDialog.create();
                imageDialog.show();

            }
        });




    }

    View.OnClickListener clickListener=new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            PosterEventsRecycleViewHolder vholder = (PosterEventsRecycleViewHolder) v.getTag();
            int position = vholder.getPosition();

//            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();

        }
    };


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
