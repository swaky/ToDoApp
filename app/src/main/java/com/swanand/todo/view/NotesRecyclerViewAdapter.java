package com.swanand.todo.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.swanand.todo.DBAdapter;
import com.swanand.todo.R;
import com.swanand.todo.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swanand on 7/9/2016.
 */
public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    ArrayList<Note> notes;
    EditText detailView_title,detailView_desc;
    ImageButton detailView_Save,detailView_Cancel;
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
    public void onBindViewHolder(final NotesViewHolder holder, final int position) {
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
        holder.datetime.setText(notes.get(position).getDatetime());
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
                new AlertDialog.Builder(context)
                        .setTitle("Confirm Delete ?")
                        .setMessage("Do you really want to whatever?")
                        .setIcon(R.drawable.ic_delete_black_24dp)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context,"Edit Note",Toast.LENGTH_SHORT).show();
                final Dialog detailDialog=new Dialog(context);
                detailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                detailDialog.setContentView(R.layout.note_detail_view);
                detailView_title= (EditText) detailDialog.findViewById(R.id.noteDetailView_editText_Title);
                detailView_desc= (EditText) detailDialog.findViewById(R.id.noteDetailView_editText_Description);
                detailView_Save= (ImageButton) detailDialog.findViewById(R.id.noteDetailView_button_Update);
                detailView_Cancel=(ImageButton)detailDialog.findViewById(R.id.noteDetailView_button_Cancel);
                detailView_title.setText(notes.get(position).getTitle());
                detailView_desc.setText(notes.get(position).getDescription());

                //Updating the data into database
                detailView_Save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DBAdapter dbAdapter=new DBAdapter(context);
                        dbAdapter.openDB();
                        dbAdapter.updateNote(notes.get(position).getId(),detailView_title.getText().toString(),detailView_desc.getText().toString());
                        notes.clear();
                        Cursor cursor=dbAdapter.getAllNotes();
                        //loop and add to arraylist
                        while(cursor.moveToNext())
                        {
                            int id=cursor.getInt(0);
                            String title=cursor.getString(1);
                            String description=cursor.getString(2);
                            String datetime=cursor.getString(3);
                            Note note=new Note(id,title,description,datetime);
                            notes.add(note);
                        }
                        updateData(notes);
                       // setAnimation(holder.itemView,position);
                        dbAdapter.closeDB();
                        detailDialog.dismiss();
                    }
                });
                //to dismiss the dialog box
                detailView_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        detailDialog.dismiss();
                    }
                });
                detailDialog.show();
            }
        });
    }
    //slide in animation effect
    private int lastPosition=-1;
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateData(ArrayList<Note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }
}
