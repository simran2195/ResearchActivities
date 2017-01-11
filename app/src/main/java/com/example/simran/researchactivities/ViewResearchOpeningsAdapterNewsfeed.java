package com.example.simran.researchactivities;

        import android.content.Context;
        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import java.util.List;

/**
 * Created by admin on 18-Jul-16.
 */
public class ViewResearchOpeningsAdapterNewsfeed extends RecyclerView.Adapter<ViewResearchOpeningsViewHolderFromNewsfeed> {

    private List<ResearchOpeningsObject> itemList;

    Context context;
    LayoutInflater inflater;

    public ViewResearchOpeningsAdapterNewsfeed(Context context, List<ResearchOpeningsObject> itemList) {
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
    public void addAll(List<ResearchOpeningsObject> list)
    {
//        itemList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewResearchOpeningsViewHolderFromNewsfeed onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = inflater.inflate(R.layout.all_research_openings_list, parent, false);

        ViewResearchOpeningsViewHolderFromNewsfeed viewHolder = new ViewResearchOpeningsViewHolderFromNewsfeed(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewResearchOpeningsViewHolderFromNewsfeed holder, final int position)
    {
        holder.tv1.setText(itemList.get(position).getProjectName());
        holder.tv2.setText(itemList.get(position).getProfessor());
        holder.tv3.setText(itemList.get(position).getType());
        holder.imageView.setImageResource(itemList.get(position).getPhoto());
        holder.imageView.setOnClickListener(clickListener);
        holder.imageView.setTag(holder);

        /*holder.btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                ViewResearchOpeningsFragment a = new ViewResearchOpeningsFragment();
                a.removeEvent(position);
            }
        });*/
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ViewResearchOpeningsViewHolderFromNewsfeed vholder = (ViewResearchOpeningsViewHolderFromNewsfeed) v.getTag();
            int position = vholder.getPosition();

            System.out.print("*****************>>>>>> Adapter "+position);

        }
    };


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
