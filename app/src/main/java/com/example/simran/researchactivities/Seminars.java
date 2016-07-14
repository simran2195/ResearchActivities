package com.example.simran.researchactivities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Seminars extends AppCompatActivity
{

    SwipeRefreshLayout swipeContainer;
    RecyclerView recyclerView;
    WebView webView;
    int art = 0;
    private ArrayList<String> SpeakerList;
    private ArrayList<String> TitleList;
    private ArrayList<String> DateList;
    private int count;
    ProgressDialog dialog;
    public static List<SeminarItemObjects> allItems = new ArrayList<SeminarItemObjects>();
    public static int[] pictureArray = {R.drawable.s1, R.drawable.speaker2, R.drawable.speaker3, R.drawable.speaker4};
    public static boolean fetched = false;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminars);

        SpeakerList = new ArrayList<String>();
        TitleList = new ArrayList<String>();
        DateList = new ArrayList<String>();

        List<SeminarItemObjects> rowListItem = initialList();

        recyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);

        final SeminarRecyclerAdapter adapter=new SeminarRecyclerAdapter(this, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        //Layout manager for Recycler view

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
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
                if(fetched == false)
                {
                    Toast.makeText(Seminars.this, "Try again in few seconds",
                            Toast.LENGTH_LONG).show();
                    swipeContainer.setRefreshing(false);
                }
                if(fetched == true)
                {

                    adapter.clear();
                    adapter.addAll(getAllItemList());
                    addData();
                    swipeContainer.setRefreshing(false);

                }

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebViewClient(new MyClient());
        webView.setWebChromeClient(new MyChromeClient());
        webView.loadUrl("https://www.iiitd.ac.in/research/seminar");



    }





    public class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // TODO Auto-generated method stub
            super.onProgressChanged(view, newProgress);

        }
    }

    public class MyClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            System.out.println("onPageStarted : " + url);
            /*dialog = new ProgressDialog(Seminars.this,ProgressDialog.THEME_HOLO_LIGHT);

            dialog.setTitle("Please Wait ");
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
*/
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onLoadResource(view, url);
            // Toast.makeText(getApplicationContext(), "kunal",
            // Toast.LENGTH_SHORT)
            // .show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            System.out.println("onPageFinished : " + url);
            webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            // webView.pageDown(true);

            fetched = true;



        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            return shouldOverrideUrlLoading(view, url);
        }

    }

    // onCreateOption

    public class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        // MediaLogin ml=new MediaLogin();
        public void processHTML(String result) {
            // process the html as needed by the app
            if (result == null)
                return;
            if (result != null) {
//                System.out.println("result was : " + result);
                Document doc = Jsoup.parse(result);
//                System.out.println("The document is+++++++" + doc);
//                Log.e("The document is+++++++", doc.toString());
                Elements tbody = doc.select("tbody");
                Elements trs = tbody.select("tr");
                count = trs.size();
                for (int i = art; i < trs.size(); i++) {
                    Element tr = trs.get(i);
                    List<Node> nodes = tr.childNodes();

                    Elements tds = tr.select("td");
                    String speaker = tds.get(0).text();
                    SpeakerList.add(speaker);
//                    System.out.println("speaker" + speaker);

//                    Log.e("speaker", speaker.toString());
                    String title = tds.get(1).text();
                    TitleList.add(title);
                    System.out.println("title" + title);

//                    Log.e("title", title.toString());
                    String date = tds.get(2).text();
                    DateList.add(date);
                    System.out.println("date" + date);

//                    Log.e("date", date.toString());
                    Node peaker = tr.childNode(0);


                }
                Log.e("show-------------------",SpeakerList.toString()+",-----------"+TitleList.toString()+"------"+DateList.toString());
                dialog.dismiss();
                /*addHeaders();
                addData();*/
            }

        }
    }

    public void addData()
    {
        int size = SpeakerList.size();
        allItems.clear();

        for (int i = 0 ; i < size ; i++)
        {
            allItems.add(new SeminarItemObjects(TitleList.get(i),
                    addn(SpeakerList.get(i)),
                    DateList.get(i),
                    pictureArray[i%4],
                    "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                    dateParser(DateList.get(i))));
        }
    }

    public String dateParser(String dateString)
    {
//        String dateString = "January 18, 2016";
        String[] stringDate = dateString.split(",");
        String  mmdd = stringDate[0];
        String yearWithSpace = stringDate[1];
        String[] monthSplit = mmdd.split(" ");
        String month = monthSplit[0];
        String date = monthSplit[1];
        String fi = "";
//        String fi = year+","+month+","+date;
//        System.out.println(fi);

        String year[] = yearWithSpace.split(" ");

        int mm = -1;
        switch(month)
        {
            case "January":
                mm = 0;
                break;
            case "February":
                mm = 1;
                break;
            case "March":
                mm = 2;
                break;
            case "April":
                mm = 3;
                break;
            case "May":
                mm = 4;
                break;
            case "June":
                mm = 5;
                break;
            case "July":
                mm = 6;
                break;
            case "August":
                mm = 7;
                break;
            case "September":
                mm = 8;
                break;
            case "October":
                mm = 9;
                break;
            case "November":
                mm = 10;
                break;
            case "December":
                mm = 11;
                break;
        }

        fi = year[1]+","+mm+","+date;
        System.out.println(fi);
        return
    fi;
    }


    public static String addn(String str)
    {
        System.out.println(str);
        String temp[] = str.split(",");
        String toReturn = "";
        for (int i = 0 ; i < temp.length ; i++ )
        {
            toReturn += temp[i].trim()+"\n";
        }

        return toReturn;
    }

    public List<SeminarItemObjects> initialList()
    {

        allItems.add(new SeminarItemObjects("**Stochastic model of protein bursts in the lactose operon of Escherichia coli",
                "Dr. Atul Narang, \nIIT Delhi",
                "April 4, 2016",
                R.drawable.speaker3,
                "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                "2016,3,4"));


        allItems.add(new SeminarItemObjects("LOMo: Latent Ordinal Model for Facial Analysis in Videos",
                "Dr. Gaurav Sharma, \nMPI, Germany",
                "June 26, 2016",
                R.drawable.speaker2,
                "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                "2016,5,26"));


        allItems.add(new SeminarItemObjects("LOMo: Latent Ordinal Model for Facial Analysis in Videos",
                "Dr. Gaurav Sharma, \nMPI, Germany",
                "March 31, 2016",
                R.drawable.speaker4,
                "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                "2016,2,31"));

        allItems.add(new SeminarItemObjects("Stochastic model of protein bursts in the lactose operon of Escherichia coli",
                "Dr. Atul Narang, \nIIT Delhi",
                "April 4, 2016",
                R.drawable.s1,
                "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                "2016,3,4"));


        allItems.add(new SeminarItemObjects("LOMo: Latent Ordinal Model for Facial Analysis in Videos",
                "Dr. Gaurav Sharma, \nIIIT Hyderabad ",
                "March 31, 2016",
                R.drawable.speaker2,
                "The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                "2016,2,31"));

        return allItems;
    }

    public List<SeminarItemObjects> getAllItemList()
    {


        /*for(int i = 0 ; i < count ; i++)
        {
            allItems.add(new SeminarItemObjects(TitleList.get(i), SpeakerList.get(i), DateList.get(i), R.drawable.s1,"The project focuses on sequence-based string classification tasks that aim to accurately predict the DNA binding sites of proteins called transcription factors (TF) in unannotated cell contexts. Previous approaches are unable to perform such accurate predictions, since they do not consider distinctions among sequence segments from annotated (source) and unannotated (target) contexts. We therefore propose a novel method called \"Transfer String Kernel\" (TSK)  that achieves improved transcription factor binding site (TFBS) predictions using cross-context sample adaptation. TSK maps sequence patterns to a high-dimensional feature space using the discriminative mismatch string kernel framework under SVM. Labeled examples from a source (annotated) context are transferred to a target (unannotated) context by re-weighting source samples adaptively. We have experimentally verified TSK's ability of TFBS identifications for different TFs under a cross-organism setting. We find that TSK consistently outperforms the state-of-the-art TFBS prediction tools, especially when working with TFs whose sequences do not conserve across contexts. We also demonstrate the generalizability of our model by showing its cutting-edge performance on a different set of cross-context  tasks for peptide binding prediction.",
                    "2016,6,1"));

        }*/


        return allItems;
    }


}
