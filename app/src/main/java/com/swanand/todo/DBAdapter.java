package com.swanand.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            contentValues.put(Constants.NDATETIME,getDateTime());
            return sqLiteDatabase.insert(Constants.NOTES_TB_NAME,Constants.ROW_ID,contentValues);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();
        return dateFormat.format(date);
    }
    public long updateNote(int id,String title,String description)
    {
        try{
                ContentValues contentValues=new ContentValues();
                contentValues.put(Constants.TITLE,title);
                contentValues.put(Constants.DESCRIPTION,description);
                contentValues.put(Constants.NDATETIME,getDateTime());
                return sqLiteDatabase.update(Constants.NOTES_TB_NAME,contentValues,Constants.ROW_ID+" =?",new String[]{String.valueOf(id)});
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
        String[] columns={Constants.ROW_ID,Constants.TITLE,Constants.DESCRIPTION,Constants.NDATETIME};
        //order in descending order
        return sqLiteDatabase.query(Constants.NOTES_TB_NAME,columns,null,null,null,null,Constants.NDATETIME+" DESC");

    }
    public void deleteNote(int noteid)
    {
        sqLiteDatabase.execSQL("DELETE FROM "+Constants.NOTES_TB_NAME+" WHERE "+Constants.ROW_ID+"="+noteid+";");
    }

    public void Clean()
    {
            sqLiteDatabase.execSQL("DELETE from "+Constants.NOTES_TB_NAME);
    }
}
