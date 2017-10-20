package com.example.x550vx_dm066t.myapplication.controller.news_controller;

import android.os.AsyncTask;

import com.example.x550vx_dm066t.myapplication.property.comments;
import com.example.x550vx_dm066t.myapplication.property.story;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by x550vx-dm066t on 20/10/2017.
 */

public class BaseAsynController extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }
    public URL callGetMethod(String urls){
        try {
            HttpURLConnection urlConnection = null;
            URL url = new URL(urls);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readResult(URL url){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            String s = sb.toString();
            return s;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public story getStory(String json){
        try{
            story news = new story();
            JSONObject jo = new JSONObject(json);
            String kids = "";
            if(jo.has("kids")){
                JSONArray arry = jo.getJSONArray("kids");
                for(int i = 0;i<arry.length();i++){
                    if(i == 0)
                        kids = arry.getString(i);
                    else
                        kids = kids+","+arry.getString(i);
                }
            }
            news.setID(jo.getString("id"));
            news.seTitle(jo.getString("title"));
            news.setType(jo.getString("type"));
            news.setKids(kids);
            news.setBy(jo.getString("by"));
            news.set_score(jo.getString("score"));
            return news;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public comments getComments(String msg, String type){
        try{
        JSONObject json = new JSONObject(msg);
        comments comment = new comments();
        comment.setId(json.get("id").toString());
        comment.setBy(json.get("by").toString());
        comment.setText(json.get("text").toString());
        comment.setTime(json.get("time").toString());
        comment.setType(json.get("type").toString());
        String kids = "";
        if (json.has("kids")) {
            JSONArray a = json.getJSONArray("kids");
            for (int i = 0; i < a.length(); i++) {
                if (i == 0)
                    kids = a.getString(i);
                else {
                    kids += "," + a.getString(i);
                }
            }
        }
        comment.setKids(kids);
        return comment;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
