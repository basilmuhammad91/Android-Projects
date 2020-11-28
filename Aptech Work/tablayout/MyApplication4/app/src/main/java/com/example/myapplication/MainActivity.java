package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewParent;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    ArrayList<Batch> lstBatches=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);


        lstBatches.add(new Batch("EE201801E",new ArrayList<String>(Arrays.asList("Basil","Sami","Umair","Qaswa","Shumaila"))));
        lstBatches.add(new Batch("PR201901B",new ArrayList<String>(Arrays.asList("Noman","Hasnain","Jawairia","Amama","Haider Rizvi","Majid"))));
        lstBatches.add(new Batch("PR201904B",new ArrayList<String>(Arrays.asList("Huma","Rabiya","Zaid","Waleed","Aisha"))));









        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        for(Batch batch:lstBatches)
        {
            viewPagerAdapter.add(batch.batchCode,new FragmentFirst(batch.lstStudents,this));
        }




        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }

    class  ViewPagerAdapter extends FragmentPagerAdapter
    {
        ArrayList<String> lstTitles=new ArrayList<>();
        ArrayList<Fragment> lstFragments=new ArrayList<>();

        public  void  add(String title,Fragment fragment)
        {
            lstTitles.add(title);
            lstFragments.add(fragment);
        }

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return lstFragments.get(position);
        }

        @Override
        public int getCount() {
            return lstFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return lstTitles.get(position);
        }
    }




}
