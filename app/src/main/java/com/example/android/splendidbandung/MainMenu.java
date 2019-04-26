package com.example.android.splendidbandung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();

    }

    private static final String TAG = "MainMenu";
    RecyclerView recyclerView;
    HotPlacesAdapter mAdapter;
    private Intent getDN;
    private String dn = "Guest";
    private int jam;
    private TextView txDisplayName;
    private ArrayList<HotPlaces> hotPlacesArrayList;
    private ViewPager mViewPager;
    private TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
    private TabLayout mTabLayout;
    private DrawerLayout sidebar;
    String currentTheme;
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("theme", MODE_PRIVATE);
        if (!currentTheme.equals(preferences.getString("theme", "green"))) {
            recreate();
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Theme Controller Start
        SharedPreferences sharedPreferences = getSharedPreferences("theme", MODE_PRIVATE);
        currentTheme = sharedPreferences.getString("theme", "green");
        switch (currentTheme) {
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
        //Theme Controller End
        setContentView(R.layout.activity_main_menu);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, SettingsActivity.class));
            }
        });
        if (acct != null) {
            dn = acct.getDisplayName();
            Log.d("onStart", acct.getDisplayName());
        } else {
            Intent intent = new Intent(MainMenu.this, Sign_In.class);
            startActivityForResult(intent, 4000);
        }
        FirebaseApp.initializeApp(this);
        txDisplayName = findViewById(R.id.greetings);
        displayName();
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);
        tabsAdapter.addFragment(new TravelFragment(), "Travel");
        tabsAdapter.addFragment(new TimelineFragment(), "Timeline");
        tabsAdapter.addFragment(new LaporanFragment(), "Laporan");
        mViewPager.setAdapter(tabsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 4000) {
            if (resultCode == RESULT_OK) {
                Task<GoogleSignInAccount> acct = GoogleSignIn.getSignedInAccountFromIntent(data);
                dn = acct.getResult().getDisplayName();
                Log.d(TAG, acct.getResult().getDisplayName());
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayName() {
        String[] sapa = getResources().getStringArray(R.array.greetings);
        jam = getJam();
        String kemana = dn + ". \nMau kemana?";
        if (jam >= 4 && jam < 12) {
            txDisplayName.setText(sapa[0] + ", " + kemana);
        } else if (jam >= 12 && jam < 15) {
            txDisplayName.setText(sapa[1] + ", " + kemana);
        } else if (jam >= 15 && jam < 19) {
            txDisplayName.setText(sapa[2] + ", " + kemana);
        } else {
            txDisplayName.setText(sapa[3] + ", " + kemana);
        }
    }

    private int getJam() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
