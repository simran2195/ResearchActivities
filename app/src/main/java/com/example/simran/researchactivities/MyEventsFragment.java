package com.example.simran.researchactivities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment
{

    static int count = 0;
    RecyclerView recyclerView;
    static SwipeRefreshLayout swipeContainer;
    static View rootView;
    public static List<SeminarItemObjects> rowListItem;
    public static List<SeminarItemObjects> myEventItems = new ArrayList<SeminarItemObjects>();


    public MyEventsFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_my_events, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(count == 0)
        {
            rowListItem = getInitialList();

        }
        else
        {
            rowListItem = getMyEventItemList();

        }
        final MyEventsAdapter adapter = new MyEventsAdapter(getActivity(), rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        super.onViewCreated(view, savedInstanceState);


        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
//                adapter.addAll(getMyEventItemList());
//                addData();
                adapter.addAll(getMyEventItemList());
                swipeContainer.setRefreshing(false);

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void refresh()
    {
        MyEventsAdapter adapter = new MyEventsAdapter(getActivity(), rowListItem);
        adapter.addAll(getMyEventItemList());
        swipeContainer.setRefreshing(false);
    }

    public void addEvent(int position)
    {
        if(count == 0)
        {
            myEventItems.clear();
        }

        if((myEventItems.size()==1)&&(myEventItems.get(0).getSpeaker())=="")
        {
            myEventItems.remove(0);
        }

        Seminars s = new Seminars();
        List<SeminarItemObjects> allItems = s.getAllItemList();
        myEventItems.add(allItems.get(position));
        ++count;
//        System.out.println("*******"+count);
//        System.out.println(allItems.get(position).getTitle()+"$$$$ From My events fragment"+" -> "+ allItems.get(position).getSpeaker()+" -> "+allItems.get(position).getDate());
//        swipeContainer.setRefreshing(true);
//        refresh();
//        swipeContainer.setRefreshing(false);

    }

    public void removeEvent(int position)
    {
        myEventItems.remove(position);
        if(myEventItems.size()==0)
        {
            addEmptyCard();
        }

//        swipeContainer.setRefreshing(true);
//        refresh();
//        swipeContainer.setRefreshing(false);


    }

    public void addEmptyCard()
    {
        myEventItems.add(new SeminarItemObjects("Add events to your list!",
                "",
                "",
                R.drawable.plusicon2,
                "",
                ""));

    }

    private List<SeminarItemObjects> getInitialList()
    {
        myEventItems.add(new SeminarItemObjects("Add events to your list!",
                "",
                "",
                R.drawable.plusicon2,
                "",
                ""));


        return myEventItems;
    }

    private List<SeminarItemObjects> getMyEventItemList()
    {

        return myEventItems;
    }



}
