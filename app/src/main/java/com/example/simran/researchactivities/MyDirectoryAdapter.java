package com.example.simran.researchactivities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simran on 8/30/16.
 */
public class MyDirectoryAdapter extends RecyclerView.Adapter<MyDirectoryRecyclerViewHolder>
{

    private List<DirectoryItemObjects> itemList;

    Context context;
    LayoutInflater inflater;


    public MyDirectoryAdapter(Context context, List<DirectoryItemObjects> itemList)
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
    public void addAll(List<DirectoryItemObjects> list)
    {
//        itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyDirectoryRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.mydirectory_list, parent, false);

        MyDirectoryRecyclerViewHolder viewHolder = new MyDirectoryRecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyDirectoryRecyclerViewHolder holder, final int position)
    {

        holder.tv1.setText(itemList.get(position).getName());
        holder.tv2.setText(itemList.get(position).getDesignation());
        holder.tv3.setText(itemList.get(position).getExtension());
        holder.tv4.setText(itemList.get(position).getRoom());
        holder.tv5.setText(itemList.get(position).getEmail());

        final String phno = holder.tv3.getText().toString();
        final String mailto = holder.tv5.getText().toString();

        holder.call.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phno));
                context.startActivity(intent);
            }
        });

        holder.emailsign.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",mailto, null));
                context.startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MyDirectoryRecyclerViewHolder vholder = (MyDirectoryRecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

        }
    };


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setFilter(List<DirectoryItemObjects> countryModels) {
        itemList = new ArrayList<>();
        itemList.addAll(countryModels);
        notifyDataSetChanged();
    }
}

