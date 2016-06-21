package com.example.llw.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.com.MyLinearadapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerviewId;
    public LinearLayoutManager linearLayoutManager = null;
    public MyLinearadapter myLinearadapter = null;
    public List<Bean> list = null;
    String[] date = {"0", "1", "2", "3", "4", "5"};

    private void assignViews() {
        recyclerviewId = (RecyclerView) findViewById(R.id.recyclerview_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        do_work();
    }

    public void do_work() {
        linearLayoutManager = new LinearLayoutManager(this);

        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Bean("item" + i));
        }
        myLinearadapter = new MyLinearadapter(this, list);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view_header = layoutInflater.inflate(R.layout.headerview_item, null);
        myLinearadapter.addheaderview(view_header);

        View view_foot = layoutInflater.inflate(R.layout.footview_item, null);
        myLinearadapter.addfootview(view_foot);

        recyclerviewId.setLayoutManager(linearLayoutManager);
        recyclerviewId.setAdapter(myLinearadapter);

        myLinearadapter.getrecyclerviewlistener(new MyLinearadapter.OnRecyclerviewListener() {
            @Override
            public void Onitem(View view) {
                Toast.makeText(MainActivity.this, "the postion is" + recyclerviewId.getChildAdapterPosition(view), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
