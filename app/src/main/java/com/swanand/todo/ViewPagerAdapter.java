package com.swanand.todo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by swanand on 7/4/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragmentList=new ArrayList<>();
    private ArrayList<String> titlesList=new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titlesList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
       // return titlesList.get(position);
    return null;
    }
}
