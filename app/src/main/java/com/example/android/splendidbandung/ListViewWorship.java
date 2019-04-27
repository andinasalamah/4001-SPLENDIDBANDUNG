//package com.example.android.splendidbandung;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//
//public class ListViewWorship extends AppCompatActivity implements AdapterView.OnItemClickListener {
//    ListViewWorship ListViewWorship;
//    ArrayAdapter<CharSequence> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_view_worship);
//
//        ListViewWorship = findViewById(R.id.ListViewWorship);
//        adapter = ArrayAdapter.createFromResource(this,R.array.Worship,android.R.layout.simple_list_item_1);
//        ListViewWorship.setAdapter(adapter);
//        ListViewWorship.setOnItemClickListener(this);
//    }
//
//}
