package com.example.x550vx_dm066t.myapplication.controller.news_controller;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.x550vx_dm066t.myapplication.NewsActivity;
import com.example.x550vx_dm066t.myapplication.controller.DB_controller.news_DB_controller;
import com.example.x550vx_dm066t.myapplication.interfaces.OnTaskCompleted;
import com.example.x550vx_dm066t.myapplication.property.story;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by x550vx-dm066t on 19/10/2017.
 */

public class NormalAsyncRequest extends BaseAsynController {
    private String url;
    private OnTaskCompleted listener;
    private ProgressDialog dialog;
    private news_DB_controller con;
    private String type;

    public NormalAsyncRequest(String _url, OnTaskCompleted _listener, ProgressDialog _dialog,news_DB_controller _con, String _type){
        url = _url;
        listener = _listener;
        dialog = _dialog;
        con = _con;
        type = _type;
    }

    @Override
    protected void onPreExecute() {
        if(!dialog.isShowing())
            dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Object[] params) {
        try {
                URL tmp_url = callGetMethod(url);
                String tmp_msg = readResult(tmp_url);
            if(type.equals("story")){
                story news = getStory(tmp_msg);
                con.addStory(news);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        NewsActivity.count++;
        if(NewsActivity.count>=15) {
            listener.onTaskCompleted();
            dialog.dismiss();
        }
    }

}
