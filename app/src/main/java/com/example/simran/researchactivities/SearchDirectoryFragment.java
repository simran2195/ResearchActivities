package com.example.simran.researchactivities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SearchDirectoryFragment extends Fragment implements SearchView.OnQueryTextListener
{

    RecyclerView recyclerview;
    List<DirectoryItemObjects> directoryItemObjectsList;
    MyDirectoryAdapter adapter;

    public SearchDirectoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_directory, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
//        String[] locales = Locale.getISOCountries();
        directoryItemObjectsList = new ArrayList<>();

//        for (String countryCode : locales) {
//            Locale obj = new Locale("", countryCode);
//            directoryItemObjectsList.add(new DirectoryItemObjects());
//        }

        TabDirectoryFragment a = new TabDirectoryFragment();
        directoryItemObjectsList = a.getAllResearchOpenings();

        adapter = new MyDirectoryAdapter(getActivity(), directoryItemObjectsList);
        recyclerview.setAdapter(adapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_directory, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(directoryItemObjectsList);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<DirectoryItemObjects> filteredModelList = filter(directoryItemObjectsList, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<DirectoryItemObjects> filter(List<DirectoryItemObjects> models, String query) {
        query = query.toLowerCase();

        final List<DirectoryItemObjects> filteredModelList = new ArrayList<>();
        for (DirectoryItemObjects model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)|| model.getDesignation().toLowerCase().contains(query) || model.getEmail().toLowerCase().contains(query) || model.getRoom().toLowerCase().contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}



