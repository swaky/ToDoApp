package com.swanand.todo.fragments;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.swanand.todo.DBAdapter;
import com.swanand.todo.model.Note;
import com.swanand.todo.view.NotesRecyclerViewAdapter;
import com.swanand.todo.R;

import java.util.ArrayList;

/**
 * Created by swanand on 7/4/2016.
 */
public class NotesFragment extends Fragment{

    private EditText editText_Title;
    private EditText editText_Desc;
    private ImageButton btn_Add;
    private DBAdapter dbAdapter;

    RecyclerView recyclerView;
    ArrayList<Note> notes;
    NotesRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        ImageButton footer_addbutton=(ImageButton)getActivity().findViewById(R.id.btn_add);
        notes=new ArrayList<>();
        //show dialog
        footer_addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        View v=inflater.inflate(R.layout.notes_layout,container,false);

         recyclerView= (RecyclerView) v.findViewById(R.id.notes_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        retrive();
        adapter= new NotesRecyclerViewAdapter(getActivity(),notes);
        recyclerView.setAdapter(adapter);
        //   recyclerView.setAdapter(new NotesRecyclerViewAdapter(this.getActivity(),retrive()));
        return v;
    }

    /*
    private ArrayList<Note> getDataList() {
    ArrayList<Note> notes=new ArrayList<>();
        Note note=new Note(1, "Title 1","Description 1");
        notes.add(note);
         note=new Note(2, "Title 2","Description 2");
        notes.add(note);
         note=new Note(3, "Title 1","Description 3");
        notes.add(note);
         note=new Note(4, "Title 1","Description 4");
        notes.add(note);
        return notes;
    }
*/


    private void retrive()
    {
        notes.clear();
        DBAdapter dbAdapter=new DBAdapter(getActivity());
        dbAdapter.openDB();
        //retrive from database
        Cursor cursor=dbAdapter.getAllNotes();
        //loop and add to arraylist
        while(cursor.moveToNext())
        {
            int id=cursor.getInt(0);
            String title=cursor.getString(1);
            String description=cursor.getString(2);

            Note note=new Note(id,title,description);

            notes.add(note);
        }
        if(!(notes.size()<1))
        {

            recyclerView.setAdapter(new NotesRecyclerViewAdapter(getActivity(),notes));
        }

    }



    private void save(String title,String desc)
    {
        DBAdapter dbAdapter=new DBAdapter(getActivity());
        //open database
        dbAdapter.openDB();
        long result=dbAdapter.addNote(title,desc);
        if(result>0)
        {   editText_Title.setText("");
            editText_Desc.setText("");
        }
        else
        {
            Toast.makeText(getActivity(),"Insert failed",Toast.LENGTH_SHORT).show();
        }

        dbAdapter.closeDB();
    //refresh
        retrive();

    }

    public void showDialog()
    {
        final Dialog dialog=new Dialog(getActivity());

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

}
