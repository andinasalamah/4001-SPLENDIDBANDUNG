package com.example.android.splendidbandung.Belanja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.splendidbandung.R;

import java.util.ArrayList;
//import demo.androidpoint.com.androidcostomlist.modal.item;

public class ListBelanja extends AppCompatActivity {
    RecyclerView mRecyclerView;
    BelanjaAdapter mAdapter;
    ArrayList<Belanja> list;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_belanja);
        mRecyclerView = (RecyclerView)findViewById(R.id.listBelanja);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*click on Home button enable and set title for this Activity*/
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Material Design CardView");

        /*initialised list for costom list and add data*/
        list = new ArrayList<Belanja>();
        list.add(new Belanja("23 Paskal","Jl. Pasir Kaliki No.23, Arjuna, Cicendo, Kota Bandung, Jawa Barat 40172",R.drawable.paskal));
        list.add(new Belanja("Bandung Indah Plaza","Jl. Merdeka No.56, Citarum, Bandung Wetan, Kota Bandung, Jawa Barat 40115",R.drawable.bip));
        list.add(new Belanja("Paris van Java","Jalan Sukajadi, Cipedes, Sukajadi, Kota Bandung, Jawa Barat 40162",R.drawable.pvj));


        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new BelanjaAdapter(list, R.layout.adaptor_item, this);
        mRecyclerView.setAdapter(mAdapter);
    }
    /*create options Menu on your Activity*/
    //ini komen hehehehe
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