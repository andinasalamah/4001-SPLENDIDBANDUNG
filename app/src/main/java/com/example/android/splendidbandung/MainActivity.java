package com.example.android.splendidbandung;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Categories> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = new ArrayList<>();
        categories.add(new Categories("Penginapan", R.drawable.penginapan));
        categories.add(new Categories("Belanja", R.drawable.belanja));
        categories.add(new Categories("Stasiun", R.drawable.stasiun));
        categories.add(new Categories("Bandara", R.drawable.bandara));
        categories.add(new Categories("Rumah Makan", R.drawable.rumah_makan));
        categories.add(new Categories("Wisata Alam", R.drawable.wisata_alam));
        categories.add(new Categories("Travel", R.drawable.travel));
        categories.add(new Categories("Tempat Ibadah", R.drawable.tempat_ibadah));
        categories.add(new Categories("Rekreasi", R.drawable.rekreasi));
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, categories);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }
}
