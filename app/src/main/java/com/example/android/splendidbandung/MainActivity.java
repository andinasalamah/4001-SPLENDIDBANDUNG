package com.example.android.splendidbandung;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Book> lstBook ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstBook = new ArrayList<>();
        lstBook.add(new Book("Penginapan","Categorie Book","Description book",R.drawable.penginapan));
        lstBook.add(new Book("Belanja","Categorie Book","Description book",R.drawable.belanja));
        lstBook.add(new Book("Stasiun","Categorie Book","Description book",R.drawable.stasiun));
        lstBook.add(new Book("Bandara","Categorie Book","Description book",R.drawable.bandara));
        lstBook.add(new Book("Rumah Makan","Categorie Book","Description book",R.drawable.rumah_makan));
        lstBook.add(new Book("Wisata Alam","Categorie Book","Description book",R.drawable.wisata_alam));
        lstBook.add(new Book("Travel","Categorie Book","Description book",R.drawable.travel));
        lstBook.add(new Book("Tempat Ibadah","Categorie Book","Description book",R.drawable.tempat_ibadah));
        lstBook.add(new Book("Rekreasi","Categorie Book","Description book",R.drawable.rekreasi));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);


    }
}
//package com.example.android.splendidbandung;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}
