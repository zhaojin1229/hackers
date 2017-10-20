package com.example.x550vx_dm066t.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.x550vx_dm066t.myapplication.controller.DB_controller.news_DB_controller;
import com.example.x550vx_dm066t.myapplication.controller.news_controller.newsRetriever;
import com.example.x550vx_dm066t.myapplication.interfaces.OnTaskCompleted;
import com.example.x550vx_dm066t.myapplication.property.comments;

import java.util.ArrayList;

public class NewsActivity extends Activity implements OnTaskCompleted{
    private newsRetriever callNews;
    private ListView list_news;
    private ClientCursorAdapter adapter;
    private Cursor cursor;
    private TextView tv_page;
    private Button next, pre;
    private ArrayList<String> kid;
    public static int pages = 20, count = 0,current = 1, max_pages = 1;
    news_DB_controller con;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kid = new ArrayList<String>();
        tv_page = (TextView) findViewById(R.id.tv_page);
        next= (Button) findViewById(R.id.btn_next);
        pre= (Button) findViewById(R.id.btn_pre);
        list_news = (ListView) findViewById(R.id.list_news);
        con = new news_DB_controller(NewsActivity.this, "NEWS_LIST");
        setDialog();
        setOnclickListener();
        pullPages();
    }

    public void setOnclickListener(){
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current>1){
                    current--;
                    pullPages();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current<max_pages){
                    current++;
                    pullPages();
                }
            }
        });
        list_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = kid.get(position);
                if(!value.equals("")){
                    Intent i = new Intent(NewsActivity.this, CommentsActivity.class);
                    i.putExtra("comments",value);
                    startActivity(i);
                }
            }
        });
    }

    private void setDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onTaskCompleted() {
        cursor = con.getStory();
        adapter = new ClientCursorAdapter(NewsActivity.this, cursor);
        list_news.setAdapter(adapter);
        tv_page.setText(""+current+"/"+max_pages+" pages");
    }

    @Override
    public void OnReplyCompleted(comments c, String pos) {}

    public class ClientCursorAdapter extends CursorAdapter {
        private LayoutInflater cursorInflater;
        public ClientCursorAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            cursorInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            return cursorInflater.inflate(R.layout.list_row, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_title.setText(cursor.getString(cursor.getColumnIndex("TITLE")));

            TextView txt_by = (TextView) view.findViewById(R.id.txt_by);
            txt_by.setText("By " + cursor.getString(cursor.getColumnIndex("BY")));
            String kids =cursor.getString(cursor.getColumnIndex("KIDS"));
            int comments = 0;
            if(!kids.equals("")){
                String[] s = kids.split(",");
                comments = s.length;
            }
            kid.add(kids);
            TextView tv_comment = (TextView) view.findViewById(R.id.tv_comment);
            tv_comment.setText("Comment: " + comments + " Score:"+cursor.getString(cursor.getColumnIndex("SCORE")));
        }
    }

    private void pullPages(){
        callNews = new newsRetriever(NewsActivity.this, NewsActivity.this, dialog,getString(R.string.api_news_list));
        callNews.execute();
    }
}
