package com.example.simran.researchactivities;

/**
 * Created by vikas on 15-06-2016.
 */


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoapParse extends AppCompatActivity {
    WebView webView;
    int art = 0;
    private ArrayList<String> SpeakerList;
    private ArrayList<String> TitleList;
    private ArrayList<String> DateList;

    ProgressDialog dialog;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);

       /* CallJSoupService obj = new CallJSoupService();
        obj.execute(new String[]{"PARAMS"});*/
        SpeakerList = new ArrayList<String>();
        TitleList = new ArrayList<String>();
        DateList = new ArrayList<String>();




        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebViewClient(new MyClient());
        webView.setWebChromeClient(new MyChromeClient());
        // webView.loadUrl("https://www.instagram.com");
       webView.loadUrl("https://www.iiitd.ac.in/research/seminar");

//        tl = (TableLayout) findViewById(R.id.maintable);

       /* CallJSoupService obj = new CallJSoupService();
        obj.execute(new String[]{"PARAMS"});*/
    /*  Handler a = new Handler();
        a.postDelayed(new Runnable() {
            @Override
            public void run() {

                addHeaders();
                addData();
            }
        },30000);*/

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
           dialog = new ProgressDialog(JsoapParse.this,ProgressDialog.THEME_HOLO_LIGHT);

            dialog.setTitle("Please Wait ");
            dialog.setMessage("Loading...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();

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
            Toast.makeText(getApplicationContext(),
                    "this is my last pageFinish", Toast.LENGTH_SHORT).show();



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
        public void processHTML(String result)
        {
            // process the html as needed by the app
            if (result == null)
                return;
            if (result != null)
            {
                System.out.println("result was : " + result);
                Document doc = Jsoup.parse(result);
                System.out.println("The document is+++++++" + doc);
                Log.e("The document is+++++++", doc.toString());
                Elements tbody = doc.select("tbody");
                Elements trs = tbody.select("tr");
                for (int i = art; i < trs.size(); i++) {
                    Element tr = trs.get(i);
                    List<Node> nodes = tr.childNodes();

                    Elements tds = tr.select("td");
                    String speaker = tds.get(0).text();
                    SpeakerList.add(speaker);
                    System.out.println("speaker" + speaker);

                    Log.e("speaker", speaker.toString());
                    String title = tds.get(1).text();
                    TitleList.add(title);
                    System.out.println("title" + title);

                    Log.e("title", title.toString());
                    String date = tds.get(2).text();
                    DateList.add(date);
                    System.out.println("date" + date);

                    Log.e("date", date.toString());
                    Node peaker = tr.childNode(0);


                }
                Log.e("show-------------------",SpeakerList.toString()+",-----------"+TitleList.toString()+"------"+DateList.toString());
                dialog.dismiss();
                /*addHeaders();
                addData();*/
            }

        }
    }




}
