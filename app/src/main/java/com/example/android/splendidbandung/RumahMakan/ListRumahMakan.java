package com.example.android.splendidbandung.RumahMakan;
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

public class ListRumahMakan extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RumahMakanAdapter mAdapter;
    ArrayList<RumahMakan> list;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rumahmakan);
        mRecyclerView = (RecyclerView)findViewById(R.id.listrumahmakan);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*click on Home button enable and set title for this Activity*/
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Material Design CardView");

        /*initialised list for costom list and add data*/
        list = new ArrayList<RumahMakan>();
        list.add(new RumahMakan("Abuba Steak Buah Batu","Jl. Pelajar Pejuang 45 No.70, Turangga, Lengkong, Kota Bandung, Jawa Barat 40262", R.drawable.abuba));
        list.add(new RumahMakan("Karnivor Bandung","LLRE Martadinata Street No.127, Cihapit, Bandung Wetan, Bandung City, West Java 40114", R.drawable.karnivor));
        list.add(new RumahMakan("The Parlor Bandung","Dago, Jalan Raya Rancakendal Luhur No.9, Ciburial, Coblong, Bandung, Jawa Barat 40191", R.drawable.parlor));
        list.add(new RumahMakan("One Eighty Coffee Music","Jl. Ganeca No.3, Lb. Siliwangi, Coblong, Kota Bandung, Jawa Barat 40132", R.drawable.oneeighty));


        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new RumahMakanAdapter(list, R.layout.adaptor_item, this);
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