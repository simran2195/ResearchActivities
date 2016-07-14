package com.example.simran.researchactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewResearchNewsDetails extends AppCompatActivity
{

    RecyclerView recyclerView;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_research_news_details);

        position = getIntent().getIntExtra("value", 0);



        List<ResearchNewsItemObjects> rowListItem = getAllItemList();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        ViewResearchNewsRecyclerAdapter adapter = new ViewResearchNewsRecyclerAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<ResearchNewsItemObjects> getAllItemList()
    {
        ResearchNews s = new ResearchNews();
        List<ResearchNewsItemObjects> allItems = s.getAllItemList();
        List<ResearchNewsItemObjects> toDisplay = new ArrayList<ResearchNewsItemObjects>();
        toDisplay.add(allItems.get(position));
        return toDisplay;
    }

}
