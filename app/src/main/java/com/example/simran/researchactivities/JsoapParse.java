package com.example.simran.researchactivities;

/**
 * Created by vikas on 15-06-2016.
 */


import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TableRow.LayoutParams;

public class JsoapParse extends AppCompatActivity {
    WebView webView;
    int art = 0;
private ArrayList<String> SpeakerList;
    private ArrayList<String> TitleList;
    private ArrayList<String> DateList;

    TableLayout tl;
    TableRow tr;
   // TextView companyTV,valueTV;

    TextView speakers,Titles;

    ProgressDialog dialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        // MediaLogin ml=new MediaLogin();
        public void processHTML(String result) {
            // process the html as needed by the app
            if (result == null)
                return;
            if (result != null) {
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

    /** This function add the headers to the table **/
    public void addHeaders(){

        /** Create a TableRow dynamically **/
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating a TextView to add to the row **/
        TextView speakers = new TextView(this);
        speakers.setText("Speaker");
        speakers.setTextColor(Color.GRAY);
        speakers.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        speakers.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        speakers.setPadding(5, 5, 5, 0);
        tr.addView(speakers);  // Adding textView to tablerow.

        /** Creating another textview **/
        TextView Titles = new TextView(this);
        Titles.setText("Title");
        Titles.setTextColor(Color.GRAY);
        Titles.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        Titles.setPadding(5, 5, 5, 0);
        Titles.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(Titles); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        // we are adding two textviews for the divider because we have two columns
        tr = new TableRow(this);
        tr.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));

        /** Creating another textview **/
        TextView divider = new TextView(this);
        divider.setText("-----------------");
        divider.setTextColor(Color.GREEN);
        divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider.setPadding(5, 0, 0, 0);
        divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider); // Adding textView to tablerow.

        TextView divider2 = new TextView(this);
        divider2.setText("-------------------------");
        divider2.setTextColor(Color.GREEN);
        divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        divider2.setPadding(5, 0, 0, 0);
        divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        tr.addView(divider2); // Adding textView to tablerow.

        // Add the TableRow to the TableLayout
        tl.addView(tr, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));
    }

    /** This function add the data to the table **/
    public void addData(){

        for (int i = 0; i <SpeakerList.size() ; i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

            /** Creating a TextView to add to the row **/
            speakers = new TextView(this);
            //companyTV.setText("jai shree ram");
            speakers.setText(SpeakerList.get(i));
            speakers.setTextColor(Color.RED);
            speakers.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            speakers.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            speakers.setPadding(5, 5, 5, 5);
            tr.addView(speakers);  // Adding textView to tablerow.

            /** Creating another textview **/
            Titles = new TextView(this);
            Titles.setText(TitleList.get(i));
           // valueTV.setText("jai shree ram ji ");
            // valueTV.setTextColor(Color.GREEN);
            Titles.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            Titles.setPadding(5, 5, 5, 5);
            Titles.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(Titles); // Adding textView to tablerow.

            // Add the TableRow to the TableLayout
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }

//     class CallJSoupService extends AsyncTask<String , Void  , String>{
//
//       @Override
//       protected void onPostExecute(String s) {
//           super.onPostExecute(s);
//           addHeaders();
//           addData();
//
//       }
//
//       @Override
//        protected String doInBackground(String... params) {
//
//           webView = (WebView) findViewById(R.id.webView1);
//           webView.getSettings().setJavaScriptEnabled(true);
//           webView.getSettings().setDomStorageEnabled(true);
//           webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
//           webView.setWebViewClient(new MyClient());
//           webView.setWebChromeClient(new MyChromeClient());
//            webView.loadUrl("https://www.iiitd.ac.in/research/seminar");
//
//            return "";
//        }
//    }
}
