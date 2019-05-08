package com.example.android.splendidbandung.Bandara;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.splendidbandung.R;

public class DetailBandara extends AppCompatActivity {
    ImageView imageView;
    TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bandara);
        imageView = findViewById(R.id.imageButton);
        textView= findViewById(R.id.textView4);
        textView1 = findViewById(R.id.textView3);

        Intent intent = getIntent();
        String a = intent.getStringExtra("nama");
        String b = intent.getStringExtra("Detail");
        String c = intent.getStringExtra("image");

//        imageView.setImageDrawable(c);
        textView.setText(a);
        textView1.setText(b);
    }
}
