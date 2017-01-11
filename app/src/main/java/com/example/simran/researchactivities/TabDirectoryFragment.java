package com.example.simran.researchactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class TabDirectoryFragment extends Fragment
{
    View rootView;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView recyclerView;
    ViewPagerAdapter viewPagerAdapter;
    EditText input;
    static String[] filterArr;
    static String filter;
    public static List<DirectoryItemObjects> directoryNewsfeedList;
    List<DirectoryItemObjects> filteredList;
    ProgressDialog mProgress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_tab_directory, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mProgress = new ProgressDialog(getActivity());

        tabLayout = (TabLayout)rootView.findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragments(new SearchDirectoryFragment(), "Search");
        viewPagerAdapter.addFragments(new MyDirectoryFragment(), "Faculty");
        viewPagerAdapter.addFragments(new MyDirectoryFragmentFacilities(), "Facilities");
        viewPagerAdapter.addFragments(new MyDirectoryFragmentAcademics(), "Academics");
        viewPagerAdapter.addFragments(new MyDirectoryFragmentLabs(), "Labs");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

                            mProgress.dismiss();


        //FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //rootView.getContext().startActivity(new Intent(rootView.getContext(), PostOpenings.class));
                *//*Intent intent = new Intent(getActivity(), PostOpenings.class);
                startActivity(intent);*//*
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
            }
        });*/
        //fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

                //Intent intent = new Intent(getActivity(), SearchDirectoryFragment.class);
                //startActivity(intent);

//                /*AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
//
//                alert.setTitle("Search");
//
//                alert.setMessage("Enter relevant keywords (Separated by comma)");
//
//                // Set an EditText view to get user input
//                input = new EditText(getActivity());
//                alert.setView(input);
//
//
//                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                        filter  = input.getText().toString();
//                        filterArr = filter.split(", ");
//                        // Do something wit  h value!
////                        System.out.println("******************* "+ filter);
//                        viewFilteredResult();
//                    }
//                });
//
//                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        // Canceled.
//                    }
//                });
//
//                alert.show();*/

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
//            }
//        });

        return rootView;
    }


    public List<DirectoryItemObjects> getAllResearchOpenings()
    {
        List<DirectoryItemObjects> list = new ArrayList<DirectoryItemObjects>();
        list = addLists(MyDirectoryFragmentAcademics.myEventItems, list);
        list = addLists(MyDirectoryFragmentFacilities.myEventItems, list);
        list = addLists(MyDirectoryFragment.myEventItems, list);
        list = addLists(MyDirectoryFragmentLabs.myEventItems, list);
        return list;
    }

    private List<DirectoryItemObjects> addLists(List<DirectoryItemObjects> list1, List<DirectoryItemObjects> list2)
    {
        for(int i=0; i<list1.size(); i++)
        {
            list2.add(list1.get(i));
        }
        return list2;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

}