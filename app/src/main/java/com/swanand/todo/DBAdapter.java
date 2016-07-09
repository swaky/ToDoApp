package com.swanand.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by swanand on 7/9/2016.
 */
public class DBAdapter {
    Context context;

    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper=new DBHelper(context);
    }
    //open database
    public DBAdapter openDB(){

        try {
            sqLiteDatabase=dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    //close database;
    public void closeDB()
    {
        try {
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //insert
    public long addNote(String title,String description)
    {
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put(Constants.TITLE,title);
            contentValues.put(Constants.DESCRIPTION,description);
            return sqLiteDatabase.insert(Constants.NOTES_TB_NAME,Constants.ROW_ID,contentValues);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    //read data
    public Cursor getAllNotes()
    {
        String[] columns={Constants.ROW_ID,Constants.TITLE,Constants.DESCRIPTION};

        return sqLiteDatabase.query(Constants.NOTES_TB_NAME,columns,null,null,null,null,null);
    }
}
