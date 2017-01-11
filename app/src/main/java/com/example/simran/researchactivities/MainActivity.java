package com.example.simran.researchactivities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    // Firebase instance variables
    //private FirebaseAuth mFirebaseAuth;
    //private FirebaseUser mFirebaseUser;
    static Firebase mRootRef;

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://fir-test-d9e33.firebaseio.com/");

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new ResearchFragment(), "RESEARCH");
        //viewPagerAdapter.addFragments(new MyCalenderFragment(), "MY CALENDAR");
        //viewPagerAdapter.addFragments(new MyEventsFragment(), "RESEARCH");
        viewPagerAdapter.addFragments(new MyEventsFragment(), "MY EVENTS");
        viewPagerAdapter.addFragments(new TabDirectoryFragment(), "DIRECTORY");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }




    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*public void clickTest(View view)
    {
        Intent i = new Intent(this, JsoapParse.class);
        startActivity(i);
    }*/

    @Override
    public void onBackPressed()
    {
        // do something on back.
        moveTaskToBack(true);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //When Product action item is clicked
        if (id == R.id.action_settings)
        {
            //Create Intent for Product Activity
//            Intent productIntent = new Intent(this,ProductActivity.class);
            //Start Product Activity
//            startActivity(productIntent);

//            SignInActivity s = new SignInActivity();
//            s.signOut();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
