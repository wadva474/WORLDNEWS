package com.example.abdul_wadudmusa.drawer;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdul_wadudmusa.drawer.fragments.Business;
import com.example.abdul_wadudmusa.drawer.fragments.Music;
import com.example.abdul_wadudmusa.drawer.fragments.Politics;
import com.example.abdul_wadudmusa.drawer.fragments.Sport;
import com.example.abdul_wadudmusa.drawer.fragments.Technology;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String Country;
    ViewPager mViewPager;

    public MainActivity() {
        Country = "ng";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SectionsPagerAdapter mSectionsPagerAdapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                new SettingActivity();
                break;
            case R.id.action_Share:
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case (R.id.nigeria):
                Country = "ng";
                break;
            case (R.id.usa):
                Country = "US";
                break;
            case (R.id.france):
                Country = "FR";
                break;
            case (R.id.india):
                Country = "in";
                break;
            case (R.id.italy):
                Country = "it";
                break;
            case (R.id.israel):
                Country = "il";
                break;
            case (R.id.japan):
                Country = "jp";
                break;
            case (R.id.pakistan):
                Country = "pk";
                break;
            case (R.id.saudi):
                Country = "Sa";
                break;
            case (R.id.finland):
                Country = "fi";
                break;
            case (R.id.sudan):
                Country = "sd";
                break;
            case (R.id.brazil):
                Country = "br";
                break;
            case (R.id.zimbabwe):
                Country = "zw";
                break;
            case (R.id.germany):
                Country = "ge";
                break;
            case (R.id.jordan):
                Country = "jo";
                break;
            case (R.id.spain):
                Country = "es";
                break;
            case (R.id.sweden):
                Country = "se";
                break;
            case (R.id.qatar):
                Country = "qa";
                break;
        }
        SectionsPagerAdapter wad = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(wad);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment =null;
            switch (position) {
                case 0:
                    fragment = new Politics();
                    break;
                case 1:
                    fragment = new Sport();
                    break;
                case 2:
                    fragment = new Music();
                    break;
                case 3:
                    fragment = new Business();
                    break;
                case 4:
                    fragment = new Technology();
                    break;

            }
           return fragment;
        }


        @Override
        public int getCount() {
            return 5;
        }
    }
}


