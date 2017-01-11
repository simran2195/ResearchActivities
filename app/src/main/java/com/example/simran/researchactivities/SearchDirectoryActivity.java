package com.example.simran.researchactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SearchDirectoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TabDirectoryFragment a = new TabDirectoryFragment();
    public List<DirectoryItemObjects> tempList = a.getAllResearchOpenings();
    List<DirectoryItemObjects> rowListItem = new ArrayList<>();
    List<DirectoryItemObjects> filteredList = new ArrayList<>();
    public  MyDirectoryAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_directory);

        toolbar = (Toolbar)findViewById(R.id.toolBar2);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.search));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText t = (EditText)findViewById(R.id.search_text);
                String text = t.getText().toString();
                System.out.println(" **************** "+ text);
                search(text);
            }
        });

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                EditText t = (EditText)findViewById(R.id.search_text);
//                String text = t.getText().toString();
//                System.out.println(" **************** "+ text);
//            }
//        });




//        Firebase ref = new Firebase("https://fir-researchactivities.firebaseio.com/directory/faculty");
//        FirebaseRecyclerAdapter<DirectoryItemObjects, MyDirectoryRecyclerViewHolder> adapter =
//        new FirebaseRecyclerAdapter<DirectoryItemObjects, MyDirectoryRecyclerViewHolder>(DirectoryItemObjects.class,
//                R.layout.directory_list, MyDirectoryRecyclerViewHolder.class, ref)
//        {
//            @Override
//            protected void populateViewHolder(MyDirectoryRecyclerViewHolder myDirectoryRecyclerViewHolder, DirectoryItemObjects directoryItemObjects, int i)
//            {
////                myDirectoryRecyclerViewHolder.tv1.setText(directoryItemObjects.getName());
////                myDirectoryRecyclerViewHolder.tv2.setText(directoryItemObjects.getDesignation());
////                myDirectoryRecyclerViewHolder.tv3.setText(directoryItemObjects.getExtension());
////                myDirectoryRecyclerViewHolder.tv4.setText(directoryItemObjects.getRoom());
////                myDirectoryRecyclerViewHolder.tv5.setText(directoryItemObjects.getEmail());
//
////                myDirectoryRecyclerViewHolder.tv1.setText("Simran");
////                myDirectoryRecyclerViewHolder.tv2.setText("CEO");
////                myDirectoryRecyclerViewHolder.tv3.setText("555");
////                myDirectoryRecyclerViewHolder.tv4.setText("DisneyLand");
////                myDirectoryRecyclerViewHolder.tv5.setText("iamawesome@me.com");
//            }
//        };
//        recyclerView.setAdapter(adapter);
    }

    /*public void clickSearch()
    {
        EditText t = (EditText)findViewById(R.id.search_text);
        String text = t.getText().toString();
        System.out.println(" **************** "+ text);
    }*/

    @Override
    public void onStart()
    {
        super.onStart();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rowListItem = getInitialList();
        adapter=new MyDirectoryAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void search(String text)
    {
        System.out.println("In Search1 ******" + tempList.size() + "  Size of filtered list --> " + filteredList.size());

        for(int j = 0 ; j < tempList.size(); j++)
        {
            if((tempList != null) && (tempList.get(j).getName().toLowerCase().contains(text.toLowerCase())||tempList.get(j).getDesignation().toLowerCase().contains(text.toLowerCase()) ||tempList.get(j).getRoom().toLowerCase().contains(text.toLowerCase())||tempList.get(j).getEmail().toLowerCase().contains(text.toLowerCase())))
            {
                System.out.println("Found!!! Exists");
                //MyDirectoryFragmentAcademics.myEventItems.add(tempList.get(j));
                if (!filteredList.contains(tempList.get(j)) && (filteredList != null))
                {
                    filteredList.add(tempList.get(j));
                    System.out.println("--> Added to Filtered List " + tempList.get(j).getName());

                }


            }
        }
        rowListItem = filteredList;
        System.out.println("***** "+ filteredList.size() + "  Row List Size -> " + rowListItem.size());
        adapter.notifyDataSetChanged();
        recyclerView.invalidate();
    }

    public List<DirectoryItemObjects> getInitialList()
    {
        return tempList;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_directory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
