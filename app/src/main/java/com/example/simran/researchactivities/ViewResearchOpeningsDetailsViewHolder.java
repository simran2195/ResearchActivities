package com.example.simran.researchactivities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by admin on 20-Jul-16.
 */
public class ViewResearchOpeningsDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    TextView projectName, postedBy, guide, compensation, duration, startingMonth, domain, type, language, prereq, preferred, openingFor, summary, institute;
    ImageView imageView;

    public ViewResearchOpeningsDetailsViewHolder(View itemView)
    {
        super(itemView);
        itemView.setOnClickListener(this);
        projectName = (TextView) itemView.findViewById(R.id.projectName);
        postedBy = (TextView) itemView.findViewById(R.id.emailId);
        guide = (TextView) itemView.findViewById(R.id.guide);
        compensation = (TextView) itemView.findViewById(R.id.projectCompensation);
        duration = (TextView) itemView.findViewById(R.id.projectDuration);
        type = (TextView) itemView.findViewById(R.id.projectType);
        startingMonth = (TextView) itemView.findViewById(R.id.projectStarting);
        domain = (TextView) itemView.findViewById(R.id.projectDomain);
        language = (TextView) itemView.findViewById(R.id.projectLanguage);
        prereq = (TextView) itemView.findViewById(R.id.projectPrereq);
        preferred = (TextView) itemView.findViewById(R.id.projectPreferred);
        openingFor = (TextView) itemView.findViewById(R.id.projectOpeningFor);
        summary = (TextView) itemView.findViewById(R.id.projectSummary);
        institute = (TextView) itemView.findViewById(R.id.projectInstitute);




        imageView = (ImageView) itemView.findViewById(R.id.image);

    }

    public void onClick(View view)
    {
        int pos = getPosition();
        // Toast.makeText(view.getContext(), "Clicked **" + pos, Toast.LENGTH_SHORT).show();

//        Toast.makeText(view.getContext(), "Clicked " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}

