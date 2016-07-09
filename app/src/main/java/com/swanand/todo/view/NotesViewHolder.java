package com.swanand.todo.view;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.swanand.todo.R;

/**
 * Created by swanand on 7/9/2016.
 */
public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title;
    TextView description;
    ItemClickListener itemClickListener;

    public NotesViewHolder(View itemView) {
        super(itemView);

        title= (TextView) itemView.findViewById(R.id.textView_title);
        description= (TextView) itemView.findViewById(R.id.textView_description);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener ic)
    {
        this.itemClickListener=ic;
    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view,getLayoutPosition());
    }
}
