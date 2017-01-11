package com.example.simran.researchactivities;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ResearchNews extends AppCompatActivity
{

    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    public static List<ResearchNewsItemObjects> allItems = new ArrayList<ResearchNewsItemObjects>();
    Toolbar toolbar;
    List<ResearchNewsItemObjects> rowListItem;
    static int newsCount = 0;
    ResearchNewsRecyclerAdapter adapter;
    Firebase mRef = new Firebase("https://fir-researchactivities.firebaseio.com/researchNews");
    public static boolean fetched = false;


//    private FirebaseRecyclerAdapter<ResearchNewsItemObjects, ResearchNewsViewHolder> mFirebaseAdapter;
//    private DatabaseReference mFirebaseDataReference;
//    public static final String RESEARCHNEWS = "researchNews";

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

    //    public static int[] pictureArray = {R.drawable.s1, R.drawable.speaker2, R.drawable.speaker3, R.drawable.speaker4};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_research_news);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setRefreshing(true);

        rowListItem = initialList();

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

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ResearchNewsRecyclerAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);

//        mFirebaseDataReference = FirebaseDatabase.getInstance().getReference();
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<ResearchNewsItemObjects, ResearchNewsViewHolder>(
//                ResearchNewsItemObjects.class,
//                R.layout.research_news_list,
//                ResearchNewsViewHolder.class,
//                mFirebaseDataReference.child(RESEARCHNEWS)
//        )
//        {
//            @Override
//            protected void populateViewHolder(ResearchNewsViewHolder researchNewsViewHolder, ResearchNewsItemObjects researchNewsItemObjects, int i)
//            {
//                researchNewsViewHolder.tv1.setText(researchNewsItemObjects.getNewsTitle());
//                researchNewsViewHolder.tv2.setText(researchNewsItemObjects.getAuthors());
//            }
//        };


        mRef.addValueEventListener(new ValueEventListener()
        {
            //Fired when database changes in the Firebase database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                allItems.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String title = (String) messageSnapshot.child("title").getValue();
                    String newsAbstract = (String) messageSnapshot.child("newsAbstract").getValue();
                    String authors = (String) messageSnapshot.child("authors").getValue();
//                    Log.v("E_ITEM_FETCHED", title + " " + authors);
                    allItems.add(new ResearchNewsItemObjects(title, newsAbstract, authors, R.drawable.news1));
                    newsCount++;
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
//                fetchTimelineAsync(0);
                if (fetched == false)
                {
                    Toast.makeText(ResearchNews.this, "Try again in few seconds",
                            Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                }
                if (fetched == true) {

                    swipeContainer.setRefreshing(false);
                }

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }




    public List<ResearchNewsItemObjects> initialList()
    {


        mRef.addValueEventListener(new ValueEventListener()
        {
            //Fired when database changes in the Firebase database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                allItems.clear();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    String title = (String) messageSnapshot.child("title").getValue();
                    String newsAbstract = (String) messageSnapshot.child("newsAbstract").getValue();
                    String authors = (String) messageSnapshot.child("authors").getValue();
//                    Log.v("E_ITEM_FETCHED", title + " " + authors);
                    allItems.add(new ResearchNewsItemObjects(title, newsAbstract, authors, R.drawable.news1));
                    newsCount++;
//                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }


        });

        fetched = true;
//
//        allItems.add(new ResearchNewsItemObjects(
//                "The paper titled \"Fast and Robust Construction of 3D Architectural Models from 2D Plans\" is accepted for publication in the WSCG 2016 conference."
//                ,"In this work we present a simple and robust method to create 3D building models from a set of architectural plans. Such plans are created for human readability and thus pose some problem in automatic creation of a 3D model. We suggest a semi-automated approach for plan cleaning and provide an algorithm for alignment and stacking of the plans followed by generation of 3D building model. We show results of our method on floor plans that generate complex 3D models in near real-time."
//                ,"Jalaj Pandey and Ojaswa Sharma"
//                ,R.drawable.news1
//
//        ));
////
//        allItems.add(new ResearchNewsItemObjects(
//                "3D Surface Reconstruction from Unorganized Sparse Cross Sections\" which is accepted for publication in the Graphics Interface 2016 conference.\n"
//                ,"In this paper, we propose an algorithm for closed and smooth 3D surface reconstruction from unorganized planar cross sections. We address the problem in its full generality, and show its effectiveness on sparse set of cutting planes. Our algorithm is based on the construction of a globally consistent signed distance function over the cutting planes. It uses a split-and-merge approach utilising Hermite mean-value interpolation for triangular meshes. This work improvises on recent approaches by providing a simplified construction that avoids need for post-processing to smooth the reconstructed object boundary. We provide results of reconstruction and its comparison with other algorithms."
//                ,"Ojaswa Sharma and Nidhi Agarwal"
//                ,R.drawable.news2
//
//        ));
//
//        allItems.add(new ResearchNewsItemObjects(
//                "Adaptive multi-voltage scaling in wireless NoC for high performance low power applications authored by Hemanta Kumar Mondal;Sri Harsha Gade;Raghav Kishore and Sujay Deb published in Design, Automation & Test in Europe Conference & Exhibition (DATE), 2016"
//                ,"Networks-on-Chip (NoCs) have garnered significant interest as communication backbone for multicore processors used across a wide range of fields that demand higher computation capability. Wireless NoCs (WNoCs) by augmenting single hop, long range wireless links with wired interconnects; offer the most promising solution to reduce multi-hop long distance communication bottlenecks and opens up innumerable possibilities of topological innovations that are not possible otherwise. However, energy consumption in routers along with Wireless Interface (WI) components still remains considerably high. Specifically for large systems with many nodes in the network, a significant amount of energy is consumed by the communication infrastructure (routers, links, WIs). The usage of the routers and WIs are application dependent and for most cases performance requirements can be met without operating the whole communication infrastructure to its maximum limit. "
//                ,"Hemanta Kumar Mondal, Sri Harsha Gade, Raghav Kishore, Sujay Deb"
//                ,R.drawable.news3
//
//        ));
//
//        allItems.add(new ResearchNewsItemObjects(
//                "Paper titled “Influence of channel length scaling on InGaZnO TFTs characteristics: current-gain cutoff frequency, intrinsic voltage-gain and on-resistance” which is accepted, IEEE/OSA Journal of display technology."
//                ,"This paper presents a study concerning the role of channel length scaling on IGZO TFT technology benchmark parameters, which are fabricated at temperatures not exceeding 180oC. The parameters under investigation are unity current gain cutoff frequency, intrinsic voltage-gain and on-resistance of the bottom-gate IGZO TFTs. As the channel length varies from 160 µm to 3 µm, the measured cutoff frequency increases from 163 kHz to 111.5 MHz, which is a superior value compared to the other competing low-temperature thin-film technologies, such as organic TFTs. On the other hand, for the same transistor dimensions, the measured intrinsic voltage-gain is changing from 165 to 5.3 and the on-resistance is decreasing from 1135.6 kΩ to 26.1 kΩ. TFTs with smaller channel length (3 µm) have shown a highly negative turn-on voltage and hump in the sub-threshold region, which can be attributed to short channel effects. The results obtained here, together with their interpretation based on device physics, provide crucial information for accurate IC design, enabling an adequate selection of device dimensions to maximize the performance of different circuit building blocks assuring the multifunctionality demanded by system-on-panel concepts."
//                ,"Dr. Pydi Ganga Bahubalindruni "
//                ,R.drawable.news4
//
//        ));
//
//        allItems.add(new ResearchNewsItemObjects(
//                "Pydi Ganga Bahubalindruni co-authored the paper titled “Novel Linear Low-Power Adder with Oxide TFTs” which accepted to be presented at ISACS 2016 Conference"
//                ,"Low-power circuits are very important in various applications. They are essential to increase the life time of a battery operated systems or in wearable technology. This paper presented a low-cost, very low-power linear analog adder with oxide TFTs, which was fabricated at low temperature (not exceeding 200 degrees). The fabricated circuit performance was tested under normal ambient.\n" +
//                "A novel linear analog adder is proposed only with n-type enhancement IGZO TFTs that computes summation of four voltage signals. However, this design can be easily extended to perform summation of more number of signals, just by adding a single TFT for each additional signal in the input block. The circuit needs few number of transistors, only a single power supply irrespective of the number of voltage signals to be added, and offers good accuracy over a reasonable range of input values. The circuit was fabricated on glass substrate with the annealing temperature not exceeding 200 degrees C. The circuit performance is characterized from measurements under normal ambient at room temperature, with a power supply voltage of 12V and a load of 4 pF. The designed circuit has shown a linearity error of 2.3% (until input signal peak to peak value is 2V), a power consumption of 78 uW and a bandwidth of 115 kHz, under the worst case condition (when it is adding four signals with the same frequency). In this , it has been noticed that the second harmonic is 32 dB below the fundamental frequency component. This circuit could offer an economic alternative to the conventional approaches."
//                ,"Pydi Ganga Bahubalindruni"
//                ,R.drawable.news5
//
//        ));

        return allItems;
    }


    public List<ResearchNewsItemObjects> getAllItemList()
    {
        return allItems;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
