package com.example.x550vx_dm066t.myapplication.controller.news_controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.x550vx_dm066t.myapplication.NewsActivity;
import com.example.x550vx_dm066t.myapplication.controller.DB_controller.news_DB_controller;
import com.example.x550vx_dm066t.myapplication.interfaces.OnTaskCompleted;

import org.json.JSONArray;

import java.net.URL;

/**
 * Created by x550vx-dm066t on 15/10/2017.
 */

public class newsRetriever extends BaseAsynController {
    private String msg;
    private Context context;
    private OnTaskCompleted listener;
    private ProgressDialog dialog;
    private   news_DB_controller con;
    private JSONArray array;
    private String url;


    public newsRetriever(Context ctx, OnTaskCompleted _listener, ProgressDialog _dialog, String _url){
        context = ctx;
        listener = _listener;
        dialog = _dialog;
        url = _url;
    }

    @Override
    protected void onPreExecute() {
        if(!dialog.isShowing())
        dialog.show();
        con = new news_DB_controller(context, "NEWS_LIST");
        con.deleteStory();
        NewsActivity.count = 0;
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URL url1 = callGetMethod(url);
            msg = readResult(url1);
            array = new JSONArray(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        try{
            NewsActivity.max_pages =  array.length()/ NewsActivity.pages;
            for(int i = ((NewsActivity.current-1)* NewsActivity.pages); i<NewsActivity.current* NewsActivity.pages; i++){
                String s ="https://hacker-news.firebaseio.com/v0/item/"+array.getString(i)+".json";
                NormalAsyncRequest nor = new NormalAsyncRequest(s,listener,dialog,con,"story");
                nor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }catch (Exception E){
            E.printStackTrace();
        }
        super.onPostExecute(o);
    }
}
