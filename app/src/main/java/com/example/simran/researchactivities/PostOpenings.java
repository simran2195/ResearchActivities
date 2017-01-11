package com.example.simran.researchactivities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.Firebase;

public class PostOpenings extends AppCompatActivity
{
    private Spinner spinner, spinnerFor;
    private static final String[] paths = {"Select","BTP", "IP", "Internship","IS","UR"};
    private static final String[] openingsFor = {"Anyone","B.Tech.", "M.Tech", "PhD", "RA"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_openings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        EditText title = (EditText) findViewById(R.id.editText);
        title.requestFocus();

        ///////////////////////////
        spinner = (Spinner)findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostOpenings.this, android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);



        spinnerFor = (Spinner)findViewById(R.id.spinnerFor);
        ArrayAdapter<String> adapterFor = new ArrayAdapter<String>(PostOpenings.this, android.R.layout.simple_spinner_item, openingsFor);

        adapterFor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFor.setAdapter(adapterFor);
        //spinner.setOnItemSelectedListener(this);



        View v = findViewById(R.id.createPostBtn);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addData();
                onBackPressed();
            }
        });
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_post_openings);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
//        setSupportActionBar(toolbar);
//
//        View v = findViewById(R.id.createPostBtn);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                addData();
//                onBackPressed();
//            }
//        });
//
//    }

    public void addData()
    {
        EditText title = (EditText)findViewById(R.id.editText);
        EditText prof = (EditText)findViewById(R.id.editprof);
//        EditText type = (EditText)findViewById(R.id.edittype);
        EditText stipend = (EditText)findViewById(R.id.editcompensation);
        EditText duration = (EditText)findViewById(R.id.editduration);
        EditText startingMonth = (EditText)findViewById(R.id.editstarting);
        EditText domain = (EditText)findViewById(R.id.editdomain);
        EditText language = (EditText)findViewById(R.id.editlang);
        EditText prereq = (EditText)findViewById(R.id.editprereq);
        EditText emailid = (EditText)findViewById(R.id.editText2);
        EditText preferred = (EditText)findViewById(R.id.editpreferred);
//        EditText openingFor = (EditText)findViewById(R.id.editfor);
        EditText summary = (EditText)findViewById(R.id.editsummary);
        EditText institute = (EditText)findViewById(R.id.editinstitute);


        String type = paths[spinner.getSelectedItemPosition()];
        String openingFor = openingsFor[spinnerFor.getSelectedItemPosition()];
        ResearchOpeningsObject temp =  new ResearchOpeningsObject(
            title.getText().toString(),
            prof.getText().toString(),
            type,
            R.drawable.domainicon,
            stipend.getText().toString(),
            duration.getText().toString(),
            startingMonth.getText().toString(),
            domain.getText().toString(),
            language.getText().toString(),
            prereq.getText().toString(),
            preferred.getText().toString(),
            emailid.getText().toString(),
            openingFor,
            summary.getText().toString(),
            institute.getText().toString()
    );
        ViewResearchOpeningsFragment.myOpeningsItems.add(0, temp);



        Firebase ref = new Firebase("https://fir-researchactivities.firebaseio.com/researchOpenings").push();
        ref.setValue(temp);
//        Log.v("ADDED", "POST ADDED");

    }


//    public void onClickCreatePost()
//    {
//        onBackPressed();
//        /*Intent intent = new Intent(this, ViewResearchOpeningsFragment.class);
//        startActivity(intent);*/
//    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
