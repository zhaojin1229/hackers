package com.example.x550vx_dm066t.myapplication.controller.news_controller;

import com.example.x550vx_dm066t.myapplication.CommentsActivity;
import com.example.x550vx_dm066t.myapplication.interfaces.OnTaskCompleted;
import com.example.x550vx_dm066t.myapplication.property.comments;

import java.net.URL;


/**
 * Created by zhao.jin on 20/10/2017.
 */

public class NormalCommentsRequest extends BaseAsynController {

    private String url, msg, type, pos;
    private OnTaskCompleted listener;
    private CommentsActivity m;
    private comments com;

    public NormalCommentsRequest(String _url, OnTaskCompleted l, CommentsActivity a, String _type, String _pos) {
        url = _url;
        listener = l;
        msg = "";
        m = a;
        type = _type;
        pos = _pos;
    }
    @Override
    protected Object doInBackground(Object[] params) {connect(url);return null;}

    @Override
    protected void onPostExecute(Object o) {
        if (type.equals("comment")) {
            CommentsActivity.progress++;
            if (CommentsActivity.progress == CommentsActivity.total)
                listener.onTaskCompleted();
        } else if (type.equals("reply")) {
            listener.OnReplyCompleted(com, pos);
        }
        super.onPostExecute(o);
    }

    private void connect(String url) {
        URL url1 = callGetMethod(url);
        msg = readResult(url1);
        comments comment = getComments(msg,type);
        if (type.equals("comment")) {
            m.getData().add(comment);
        } else if (type.equals("reply")) {
            com = comment;
        }
    }
}
