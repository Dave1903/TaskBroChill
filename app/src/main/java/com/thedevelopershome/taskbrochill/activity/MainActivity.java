package com.thedevelopershome.taskbrochill.activity;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.thedevelopershome.taskbrochill.R;
import com.thedevelopershome.taskbrochill.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setShouldExpand(true);
        // Attach the view pager to the tab strip

        tabsStrip.setViewPager(viewPager);




    }
}
