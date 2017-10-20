package com.example.x550vx_dm066t.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.x550vx_dm066t.myapplication.controller.news_controller.NormalCommentsRequest;
import com.example.x550vx_dm066t.myapplication.interfaces.OnTaskCompleted;
import com.example.x550vx_dm066t.myapplication.property.comments;

import java.util.ArrayList;
import java.util.HashMap;

public class CommentsActivity extends Activity implements OnTaskCompleted {

        private ListView lst_comment;
        private String comments_id;
        private ArrayList<comments> data;
        private ProgressDialog dialog;
        public static int progress = 0, total = 0;
        private comments replies;
        private HashMap<String, comments> map;
        private  ListAdapter adapter;

    public void setReplies(comments re) {
        replies = re;
    }
    public ArrayList<comments> getData() {
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initiate();
    }

    private void initiate(){
        data = new ArrayList<comments>();
        map = new HashMap();
        comments_id = getIntent().getExtras().getString("comments");
        lst_comment = (ListView) findViewById(R.id.lst_comment);
        dialog = new ProgressDialog(CommentsActivity.this);
        dialog.setMessage("Loading...");
        dialog.show();
        if(!comments_id.equals("")){
            String[] urls = comments_id.split(",");
            progress = 0;
            total = urls.length;
            for(int i = 0; i< urls.length; i++) {
                String url = "https://hacker-news.firebaseio.com/v0/item/"+urls[i]+".json";
                NormalCommentsRequest request_task = new NormalCommentsRequest(url,CommentsActivity.this,CommentsActivity.this, "comment","");
                request_task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }
    }

    @Override
    public void onTaskCompleted() {
        if(dialog.isShowing())
            dialog.dismiss();
        adapter  = new ListAdapter(data);
        lst_comment.setAdapter(adapter);
    }

    @Override
    public void OnReplyCompleted(comments c, String pos) {
        if(dialog.isShowing())
            dialog.dismiss();
        map.put(pos,c);
        adapter.notifyDataSetChanged();
    }

    public class ListAdapter extends BaseAdapter {

        private  ArrayList<comments> data;

        public ListAdapter(ArrayList<comments> _data){
            data=_data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            if(view==null){
                view = inflater.inflate(R.layout.lst_comment_row,null) ;
            }
            comments com = data.get(position);
            TextView txt_comment = (TextView) view.findViewById(R.id.txt_comment);
            txt_comment.setText(Html.fromHtml(com.getText()));
            TextView txt_comment_by = (TextView) view.findViewById(R.id.txt_comment_by);
            txt_comment_by.setText("By "+com.getBy());
            TextView txt_reply = (TextView) view.findViewById(R.id.txt_reply);
            TextView txt_reply_content = (TextView) view.findViewById(R.id.txt_reply_content);
            txt_reply_content.setVisibility(View.GONE);
            if(!com.getKids().equals("")){
                final String[] children = com.getKids().split(",");
                txt_reply.setVisibility(View.VISIBLE);
                txt_reply.setText("Click to see first level reply");
                txt_reply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!dialog.isShowing()){
                            dialog.show();
                        }
                        String url = "https://hacker-news.firebaseio.com/v0/item/"+children[0]+".json";
                        NormalCommentsRequest request_task = new NormalCommentsRequest(url,CommentsActivity.this,CommentsActivity.this,"reply",position+"");
                        request_task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }
                });
            }else{
                txt_reply.setVisibility(View.GONE);
            }
            if(map.containsKey(""+position)){
                txt_reply_content.setVisibility(View.VISIBLE);
                comments c = map.get(""+position);
                txt_reply_content.setText(Html.fromHtml(c.getText()+"<br/><br/> By "+c.getBy()));
            }

            return view;
        }
    }
    }
