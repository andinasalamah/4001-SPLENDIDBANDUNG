package com.example.android.splendidbandung.Belanja;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splendidbandung.R;

public class DetailBelanja extends AppCompatActivity{
    ImageView imageView;
    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_belanja);
        imageView = findViewById(R.id.imageButton);
    }
}
