package org.androidtown.tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    // view pager
    ViewPager pager;
    MainViewPagerAdapter adapter;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        // viewpager
        pager = (ViewPager) findViewById(R.id.viewPager);
        adapter =  new MainViewPagerAdapter(getSupportFragmentManager());

        // add fragments
        adapter.addItem(fragment1, "첫번째");
        adapter.addItem(fragment2, "두번째");
        adapter.addItem(fragment3, "세번째");

        pager.setAdapter(adapter);


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText(adapter.getPageTitle(0));
        tabs.getTabAt(1).setText(adapter.getPageTitle(1));
        tabs.getTabAt(2).setText(adapter.getPageTitle(2));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);

                pager.setCurrentItem(position);
                toolbar.setTitle(adapter.getPageTitle(position));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }

    /**
     * Adapter for ViewPager
     */
    class MainViewPagerAdapter extends FragmentStatePagerAdapter {
        // Fragment items
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        // Page titles
        ArrayList<String> titles = new ArrayList<String>();

        /**
         * Constructor
         *
         * @param fm
         */
        public MainViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment fragment) {
            items.add(fragment);
        }

        /**
         * Add a fragment as a page
         *
         * @param fragment
         * @param title
         */
        public void addItem(Fragment fragment, String title) {
            items.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

}
