package com.swanand.todo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.swanand.todo.R;
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
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());

        //listener
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context,notes.get(position).getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
