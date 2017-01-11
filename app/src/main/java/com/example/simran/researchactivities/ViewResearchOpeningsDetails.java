package com.example.simran.researchactivities;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 20-Jul-16.
 */
public class ViewResearchOpeningsDetails extends AppCompatActivity {

    RecyclerView recyclerView;
    int position;

    Toolbar toolbar;

    private Drawable getColoredArrow()
    {
        Drawable arrowDrawable = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        Drawable wrapped = DrawableCompat.wrap(arrowDrawable);

        if (arrowDrawable != null && wrapped != null) {
            // This should avoid tinting all the arrows
            arrowDrawable.mutate();
            DrawableCompat.setTintList(wrapped, ColorStateList.valueOf(this.getResources().getColor(R.color.white)));
        }
        return wrapped;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_research_openings_details);
        position = getIntent().getIntExtra("value", 0);
        System.out.print("*****************"+position);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getColoredArrow());


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });


        List<ResearchOpeningsObject> rowListItem = getAllItemList();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        ViewResearchOpeningsDetailsAdapter adapter = new ViewResearchOpeningsDetailsAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<ResearchOpeningsObject> getAllItemList()
    {
        System.out.print("*****************"+position);
        ViewResearchOpeningsFragment s = new ViewResearchOpeningsFragment();
        List<ResearchOpeningsObject> allItems = s.getMyEventItemList();
        List<ResearchOpeningsObject> toDisplay = new ArrayList<ResearchOpeningsObject>();
        toDisplay.add(allItems.get(position));
        return toDisplay;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}