package com.example.simran.researchactivities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Simran on 5/19/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();

    public void addFragments(Fragment fragments, String  titles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(titles);

    }

    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position)
    {
        return tabTitles.get(position);
    }
}
