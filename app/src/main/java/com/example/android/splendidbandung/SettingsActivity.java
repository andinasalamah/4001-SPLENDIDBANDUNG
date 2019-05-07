package com.example.android.splendidbandung;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton green, blue, white, black;
    TextView uid;
    ImageView imgid;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);
        switch (sharedPreferences.getString("theme", "green")) {
            case "green":
                setTheme(R.style.AppTheme_NoActionBar);
                break;
            case "blue":
                setTheme(R.style.AppTheme_Blue_NoActionBar);
                break;
            case "white":
                setTheme(R.style.AppTheme_White_NoActionBar);
                break;
            case "black":
                setTheme(R.style.AppTheme_Black_NoActionBar);
                break;
        }
        setContentView(R.layout.activity_settings);
        uid = findViewById(R.id.settings_textview_namauser);
        imgid = findViewById(R.id.settings_imageview_user);
        FirebaseAuth user = FirebaseAuth.getInstance();
        uid.setText(user.getCurrentUser().getDisplayName());
        Uri userImg = user.getCurrentUser().getPhotoUrl();
        Picasso.with(this)
                .load(userImg)
                .into(imgid);
        green = findViewById(R.id.settings_btn_green);
        blue = findViewById(R.id.settings_btn_blue);
        black = findViewById(R.id.settings_btn_black);
        white = findViewById(R.id.settings_btn_white);

        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        black.setOnClickListener(this);
        white.setOnClickListener(this);

        Button btn = findViewById(R.id.settings_btn_signout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_out();
            }
        });
    }

    private void sign_out() {
        new AlertDialog.Builder(this)
                .setTitle("Keluar")
                .setMessage("Apakah Anda yakin untuk log-out?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth auth = FirebaseAuth.getInstance();
                        auth.signOut();
                        startActivity(new Intent(SettingsActivity.this, Sign_In.class));
                        finish();
                    }
                }).setNegativeButton("Tidak", null)
                .show();
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (v.getId()) {
            case R.id.settings_btn_green:
                editor.putString("theme", "green");
                setTheme(R.style.AppTheme_NoActionBar);
                break;
            case R.id.settings_btn_blue:
                editor.putString("theme", "blue");
                setTheme(R.style.AppTheme_Blue_NoActionBar);
                break;
            case R.id.settings_btn_black:
                editor.putString("theme", "black");
                setTheme(R.style.AppTheme_Black);
                break;
            case R.id.settings_btn_white:
                editor.putString("theme", "white");
                setTheme(R.style.AppTheme_White_NoActionBar);
                break;
        }
        editor.apply();
        recreate();
    }
}
