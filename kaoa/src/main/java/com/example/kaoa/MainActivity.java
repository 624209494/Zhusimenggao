package com.example.kaoa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.kaoa.fragments.Fragment1;
import com.example.kaoa.fragments.Fragment2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTable;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initvp();


    }

    private void initvp() {
        list = new ArrayList<>();
        FragmentManager fm = getSupportFragmentManager();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        list.add(fragment1);
        list.add(fragment2);


        FmAdaper adaper = new FmAdaper(fm, list);
        mVp.setAdapter(adaper);

        mTable.setupWithViewPager(mVp);

    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTable = (TabLayout) findViewById(R.id.table);
    }
}
