package com.example.simran.researchactivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResearchFragment extends Fragment
{


    RecyclerView recyclerView;


    public ResearchFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.fragment_research, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ResearchItemObjects> rowListItem = getAllItemList();
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        super.onViewCreated(view, savedInstanceState);
    }

    private List<ResearchItemObjects> getAllItemList(){

        List<ResearchItemObjects> allItems = new ArrayList<ResearchItemObjects>();
        allItems.add(new ResearchItemObjects("Seminars", R.drawable.seminar));
//        allItems.add(new ResearchItemObjects("Seminars", R.drawable.seminar2));
        allItems.add(new ResearchItemObjects("Research News", R.drawable.researchnews3));
        allItems.add(new ResearchItemObjects("Events", R.drawable.events));

//        allItems.add(new ResearchItemObjects("Research  Openings", R.drawable.researchopenings));
        allItems.add(new ResearchItemObjects("Research Openings", R.drawable.researchopenings));


        return allItems;
    }


}

