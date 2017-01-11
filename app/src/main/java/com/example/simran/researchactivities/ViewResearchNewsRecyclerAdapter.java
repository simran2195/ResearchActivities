package com.example.simran.researchactivities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Simran on 6/26/16.
 */
public class ViewResearchNewsRecyclerAdapter extends RecyclerView.Adapter<ViewResearchNewsRecyclerViewHolder>
{
    private List<ResearchNewsItemObjects> itemList;

    Context context;
    LayoutInflater inflater;

    public ViewResearchNewsRecyclerAdapter(Context context, List<ResearchNewsItemObjects> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }
    @Override
    public ViewResearchNewsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.view_research_news_details, parent, false);

        ViewResearchNewsRecyclerViewHolder viewHolder=new ViewResearchNewsRecyclerViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewResearchNewsRecyclerViewHolder holder, int position)
    {

        holder.tv1.setText(itemList.get(position).getNewsTitle());
        holder.tv2.setText(itemList.get(position).getAuthors());
        holder.tv3.setText(itemList.get(position).getNewsAbstract());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
//        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

//    View.OnClickListener clickListener=new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//
//            ViewResearchNewsRecyclerViewHolder vholder = (ViewResearchNewsRecyclerViewHolder) v.getTag();
//            int position = vholder.getPosition();
//
////            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();
//
//        }
//    };




    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


}
