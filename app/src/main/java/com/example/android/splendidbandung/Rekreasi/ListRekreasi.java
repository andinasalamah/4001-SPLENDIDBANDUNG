package com.example.android.splendidbandung.Rekreasi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.splendidbandung.R;

import java.util.ArrayList;

public class ListRekreasi extends AppCompatActivity{
    RecyclerView mRecyclerView;
    RekreasiAdapter mAdapter;
    ArrayList<Rekreasi> list;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rekreasi);
        mRecyclerView = (RecyclerView)findViewById(R.id.listrekreasi);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /*click on Home button enable and set title for this Activity*/
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Material Design CardView");

        /*initialised list for costom list and add data*/
        list = new ArrayList<Rekreasi>();
        list.add(new Rekreasi("Farmhouse Susu Lembang","Jl. Raya Lembang No.108, Gudangkahuripan, Lembang, Kabupaten Bandung Barat, Jawa Barat 40391",R.drawable.farm));
        list.add(new Rekreasi("Dusun Bambu Lembang","No.KM 11, Jl. Kolonel Masturi, Situ Lembang, Cisarua, Kabupaten Bandung Barat, Jawa Barat 40551",R.drawable.dusun));
        list.add(new Rekreasi("Orchid Forest Cikole","Cikole, Lembang, West Bandung Regency, West Java 40391",R.drawable.orchid));


        /*initialised Adapter Class and set Adapter on ListView */
        mAdapter = new RekreasiAdapter(list, R.layout.adaptor_item, this);
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
