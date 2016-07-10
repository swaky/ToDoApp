package com.swanand.todo.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swanand.todo.DBAdapter;
import com.swanand.todo.MainActivity;
import com.swanand.todo.R;
import com.swanand.todo.fragments.NotesFragment;
import com.swanand.todo.fragments.TodoFragment;
import com.swanand.todo.model.Note;

import java.util.ArrayList;

/**
 * Created by swanand on 7/9/2016.
 */
public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    ArrayList<Note> notes;

    public NotesRecyclerViewAdapter(Context context, ArrayList<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    //initialize the view holder

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row,null);
        NotesViewHolder holder=new NotesViewHolder(v);
        return holder;
    }

    //bind data to view

    @Override
    public void onBindViewHolder(NotesViewHolder holder, final int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
        //listener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context,notes.get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });
        holder.deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(context,"Delete Note",Toast.LENGTH_LONG).show();
                new AlertDialog.Builder(context)
                        .setTitle("Confirm Delete ?")
                        .setMessage("Do you really want to whatever?")
                        .setIcon(R.drawable.ic_delete_black_24dp)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(context, "Yes selected", Toast.LENGTH_SHORT).show();
                                DBAdapter dbAdapter=new DBAdapter(context);
                                dbAdapter.openDB();
                                dbAdapter.deleteNote(notes.get(position).getId());
                                dbAdapter.closeDB();
                                notes.remove(position);
                                notifyItemRemoved(position);
                                notifyItemChanged(position,notes.size());
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
        holder.editNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Edit Note",Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
