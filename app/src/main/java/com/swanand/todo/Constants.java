package com.swanand.todo;

/**
 * Created by swanand on 7/9/2016.
 */
public class Constants {

    //columns
    static final String ROW_ID="id";
    static final String TITLE="title";
    static final String DESCRIPTION="description";
    static final String NDATETIME="note_date";

    static final int DB_VERSION =1 ;
    static final String DB_NAME ="Notes" ;
    static final String NOTES_TB_NAME="notes_tb";
    static final String Todo_TB_NAME="todo_tb";

    static final String CREATE_NOTES_TB="CREATE TABLE notes_tb(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT NOT NULL,description TEXT NOT NULL,note_date TEXT NOT NULL);";

    static final String CREATE_TODO_TB="CREATE TABLE todo_tb(tid INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title TEXT NOT NULL);";
}
