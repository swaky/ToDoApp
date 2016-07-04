package com.swanand.todo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swanand.todo.fragments.NotesFragment;
import com.swanand.todo.fragments.TodoFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons={R.drawable.ic_list_white_24dp,R.drawable.ic_description_white_24dp};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         viewPager =(ViewPager) findViewById(R.id.viewpager);
        setUpViewPager(viewPager);
        tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);

    }

    private void setUpViewPager(ViewPager viewPager) {
        //write adapter for viewpager
        ViewPagerAdapter adapter =new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TodoFragment.newInstance("ToDo"),"One");
        adapter.addFragment(NotesFragment.newInstance("Notes"),"Two");
        viewPager.setAdapter(adapter);

    }
}
