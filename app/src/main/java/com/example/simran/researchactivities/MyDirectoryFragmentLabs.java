package com.example.simran.researchactivities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 30-Sep-16.
 */
public class MyDirectoryFragmentLabs extends Fragment
{

    static int count = 0;
    RecyclerView recyclerView;
    static SwipeRefreshLayout swipeContainer;
    static View rootView;
    public static List<DirectoryItemObjects> rowListItem;
    public static List<DirectoryItemObjects> myEventItems = new ArrayList<DirectoryItemObjects>();
    MyDirectoryAdapter adapter;

    Firebase mRef = new Firebase("https://fir-researchactivities.firebaseio.com/directory/labs");

    public MyDirectoryFragmentLabs()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_directory, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        rowListItem = getInitialList();
        final MyDirectoryAdapter adapter = new MyDirectoryAdapter(getActivity(), rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        super.onViewCreated(view, savedInstanceState);


//        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
//        // Setup refresh listener which triggers new data loading
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
//        {
//            @Override
//            public void onRefresh()
//            {
//                // Your code to refresh the list here.
//
//                adapter.addAll(getMyEventItemList());
//             swipeContainer.setRefreshing(false);
//
//            }
//        });
//        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);


    }

    private List<DirectoryItemObjects> getInitialList()
    {
        //adapter.addAll(getMyEventItemList());
//        myEventItems.add(new DirectoryItemObjects("Pankaj Jalote",
//                "Professor & Director",
//                "A-501",
//                "481",
//                "jalote@iiitd.ac.in",
//                R.drawable.face));

        mRef.addValueEventListener(new ValueEventListener() {
            //Fired when database changes in the Firebase database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myEventItems.clear();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String name = (String) messageSnapshot.child("Name").getValue();
                    String designation = (String) messageSnapshot.child("Designation").getValue();
                    String room = (String) messageSnapshot.child("Room").getValue();
                    String phone = (String) messageSnapshot.child("Phone").getValue();
                    String email = (String) messageSnapshot.child("Email").getValue();

                    //Log.v("E_ITEM_FETCHED", name + " " + designation);
                    myEventItems.add(new DirectoryItemObjects(name, designation, room, phone, email, R.drawable.face));
                    //newsCount++;
                    //adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return myEventItems;
    }

    private List<DirectoryItemObjects> getMyEventItemList()
    {

        //       writeList();
        //     sharedPref();
        return myEventItems;
    }

}




