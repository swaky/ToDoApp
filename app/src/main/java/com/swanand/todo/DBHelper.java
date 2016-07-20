package com.swanand.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by swanand on 7/9/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,Constants.DB_NAME,null,Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(Constants.CREATE_NOTES_TB);
            sqLiteDatabase.execSQL(Constants.CREATE_TODO_TB);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constants.NOTES_TB_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Constants.Todo_TB_NAME);
        onCreate(sqLiteDatabase);
    }
}
