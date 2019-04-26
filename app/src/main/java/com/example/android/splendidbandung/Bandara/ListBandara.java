package com.example.android.splendidbandung.Bandara;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.splendidbandung.Bandara.Bandara;
import com.example.android.splendidbandung.Bandara.BandaraAdapter;
import com.example.android.splendidbandung.R;

import java.util.ArrayList;
//import demo.androidpoint.com.androidcostomlist.modal.item;

public class ListBandara extends AppCompatActivity{
    RecyclerView mRecyclerView;
    BandaraAdapter mAdapter;
    ArrayList<Bandara> list;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bandara);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*click on Home button enable and set title for this Activity*/
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Material Design CardView");

        /*initialised list for costom list and add data*/
        list = new ArrayList<Bandara>();
        list.add(new Bandara("Manish Rajput","manish@gmail.com",R.drawable.a1));
        list.add(new Bandara("Vinit yadav","vinit@gmail.com",R.drawable.a2));
        list.add(new Bandara("Anoop","anoop@gmail.com",R.drawable.a1));
        list.add(new Bandara("Dheeraj","Dheeraj@gmail.com",R.drawable.a2));
        list.add(new Bandara("Ramu","Ramu@gmail.com",R.drawable.a1));
        list.add(new Bandara("Pankaj","manish@gmail.com",R.drawable.a2));
        list.add(new Bandara("Ketan","Ketan@gmail.com",R.drawable.a1));
        list.add(new Bandara("Akshay","Akshay@gmail.com",R.drawable.a2));

        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new BandaraAdapter(list, R.layout.adaptor_item, this);
        mRecyclerView.setAdapter(mAdapter);
    }
    /*create options Menu on your Activity*/
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}