package com.swanand.todo;

import android.app.Dialog;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.swanand.todo.fragments.NotesFragment;
import com.swanand.todo.fragments.TodoFragment;
import com.swanand.todo.model.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editText_Title;
    private EditText editText_Desc;
    private ImageButton btn_Add;


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int[] tabIcons={R.drawable.ic_list_white_24dp,R.drawable.ic_description_white_24dp};
    private ImageButton button_add;
    int tab_position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager =(ViewPager) findViewById(R.id.viewpager);
        button_add= (ImageButton) findViewById(R.id.btn_add);

        setUpViewPager(viewPager);

        tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab_position=tabLayout.getSelectedTabPosition();
                if(tab_position==0)     //if tab 1 is selected
                {
                        Toast.makeText(MainActivity.this,"To do selected",Toast.LENGTH_LONG).show();

                }
                if(tab_position==1)   //if notes tab is selected
                {
                 //   Toast.makeText(MainActivity.this,"Notes selected",Toast.LENGTH_LONG).show();
//                showDialog();
                }

            }
        });
    }
  /*  public void showDialog()
    {
        final Dialog dialog=new Dialog(this);

        //no title bar for dialog box
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_layout);
        editText_Title = (EditText) dialog.findViewById(R.id.editText_Title);
        editText_Desc= (EditText) dialog.findViewById(R.id.editText_Description);
        btn_Add= (ImageButton) dialog.findViewById(R.id.button_Done);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            save(editText_Title.getText().toString(),editText_Desc.getText().toString());
         dialog.dismiss();
            }
        });
        dialog.show();
    }
*/
    private void save(String title,String desc)
    {
        DBAdapter dbAdapter=new DBAdapter(this);
        //open database
        dbAdapter.openDB();
        long result=dbAdapter.addNote(title,desc);
        if(result>0)
        {   editText_Title.setText("");
            editText_Desc.setText("");
        }
        else
        {
            Toast.makeText(this,"Insert failed",Toast.LENGTH_SHORT).show();
        }

        dbAdapter.closeDB();


    }



    private void setUpViewPager(ViewPager viewPager) {
        //write adapter for viewpager
        ViewPagerAdapter adapter =new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TodoFragment.newInstance("ToDo"),"Todo");
        adapter.addFragment(new NotesFragment(),"Notes");
        viewPager.setAdapter(adapter);

    }


}
