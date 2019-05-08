package com.example.android.splendidbandung.TempatIbadah;
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

public class ListTempatIbadah extends AppCompatActivity {
    RecyclerView mRecyclerView;
    TempatIbadahAdapter mAdapter;
    ArrayList<TempatIbadah> list;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tempatibadah);
        mRecyclerView = (RecyclerView)findViewById(R.id.listtempatibadah);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*click on Home button enable and set title for this Activity*/
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Material Design CardView");

        /*initialised list for costom list and add data*/
        list = new ArrayList<TempatIbadah>();
        list.add(new TempatIbadah("Masjid Raya Bandung","Jl. Dalem Kaum No.14, Balonggede, Regol, Kota Bandung, Jawa Barat 40251", R.drawable.masjidraya));
        list.add(new TempatIbadah("St. Peter's Cathedral, Bandung","Jl. Merdeka No.14, Babakan Ciamis, Sumur Bandung, Kota Bandung, Jawa Barat 40117", R.drawable.gereja));
        list.add(new TempatIbadah("Pura Vira Chandra Dharma ","Hegarmanah, Cidadap, Bandung City, West Java 40141", R.drawable.pura));
        list.add(new TempatIbadah("Vihara Vipassana Bandung","Jalan Kolonel Masturi No.69, Cikahuripan, Lembang, Sukajaya, Lembang, Kabupaten Bandung Barat, Jawa Barat 40391", R.drawable.vihara));


        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new TempatIbadahAdapter(list, R.layout.adaptor_item, this);
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