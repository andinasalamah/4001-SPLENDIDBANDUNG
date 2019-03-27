package com.example.android.splendidbandung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainMenu extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        txDisplayName = findViewById(R.id.greetings);
        displayName();

        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabLayout);
        tabsAdapter.addFragment(new TravelFragment(), "Travel");
        tabsAdapter.addFragment(new TimelineFragment(), "Timeline");
        mViewPager.setAdapter(tabsAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @SuppressLint("SetTextI18n")
    private void displayName() {
        String[] sapa = getResources().getStringArray(R.array.greetings);
        jam = getJam();
        getDN = getIntent();
        dn = getDN.getStringExtra("DisplayName");
        if (dn == null) {
            dn = getString(R.string.display_guest);
        }
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

    public void newpost(View view) {
        CoordinatorLayout coor = findViewById(R.id.coor_timeline);
        Snackbar.make(coor, "Add something here", Snackbar.LENGTH_LONG).show();
    }
}
