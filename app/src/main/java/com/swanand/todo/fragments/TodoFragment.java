package com.swanand.todo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swanand.todo.R;

/**
 * Created by swanand on 7/4/2016.
 */
public class TodoFragment extends Fragment {
    private static final String ARG_EXAMPLE ="this is constant" ;
    private String example_data;

    public TodoFragment() {
    }
    public static TodoFragment newInstance(String example_args){
        TodoFragment todoFragment=new TodoFragment();
        Bundle args=new Bundle();
        args.putString(ARG_EXAMPLE,example_args);
        todoFragment.setArguments(args);
        return todoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        example_data=getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with",example_data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.todo_layout,container,false);
    }
}
