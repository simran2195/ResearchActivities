package com.example.simran.researchactivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 20-Jul-16.
 */
public class ViewResearchOpeningsDetailsAdapter extends RecyclerView.Adapter<ViewResearchOpeningsDetailsViewHolder>
{
    private List<ResearchOpeningsObject> itemList;

    Context context;
    LayoutInflater inflater;


    public ViewResearchOpeningsDetailsAdapter(Context context, List<ResearchOpeningsObject> itemList)
    {
        this.context=context;
        this.itemList = itemList;
        inflater=LayoutInflater.from(context); //****
    }

    @Override
    public ViewResearchOpeningsDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v=inflater.inflate(R.layout.viw_research_openings_details, parent, false);

        ViewResearchOpeningsDetailsViewHolder viewHolder = new ViewResearchOpeningsDetailsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewResearchOpeningsDetailsViewHolder holder, int position)
    {

        holder.projectName.setText(itemList.get(position).getProjectName());
        holder.postedBy.setText(itemList.get(position).getEmailId());
        holder.guide.setText(itemList.get(position).getProfessor());
        holder.compensation.setText(itemList.get(position).getStipend());
        holder.type.setText(itemList.get(position).getType());
        holder.duration.setText(itemList.get(position).getDuration());
        holder.startingMonth.setText(itemList.get(position).getStartingMonth());
        holder.domain.setText(itemList.get(position).getDomain());
        holder.language.setText(itemList.get(position).getLanguage());
        holder.prereq.setText(itemList.get(position).getPrereq());
        holder.preferred.setText(itemList.get(position).getPreferred());
        holder.openingFor.setText(itemList.get(position).getOpeningFor());
        holder.summary.setText(itemList.get(position).getSummary());
        holder.institute.setText(itemList.get(position).getInstitute());

        holder.imageView.setImageResource(itemList.get(position).getPhoto());
//        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);
    }

//    View.OnClickListener clickListener=new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            ViewResearchOpeningsDetailsViewHolder vholder = (ViewResearchOpeningsDetailsViewHolder) v.getTag();
//            int position = vholder.getPosition();
//
//
//
////            Toast.makeText(context,"This is position "+position,Toast.LENGTH_LONG ).show();
//        }
//    };


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
