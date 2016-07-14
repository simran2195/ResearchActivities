package com.example.simran.researchactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewSeminarDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_seminar_details);
        position = getIntent().getIntExtra("value", 0);



        List<SeminarItemObjects> rowListItem = getAllItemList();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        SeminarDetailsRecyclerAdapter adapter = new SeminarDetailsRecyclerAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<SeminarItemObjects> getAllItemList()
    {
        Seminars s = new Seminars();
        List<SeminarItemObjects> allItems = s.getAllItemList();
        List<SeminarItemObjects> toDisplay = new ArrayList<SeminarItemObjects>();
        toDisplay.add(allItems.get(position));
        return toDisplay;
    }


}