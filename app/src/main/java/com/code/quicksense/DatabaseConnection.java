package com.code.quicksense;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseConnection extends SQLiteOpenHelper {
    public DatabaseConnection(Context context) {
        super(context, "Leaderboard.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Leaderboard(uname TEXT, res TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String lb_name, String lb_res){
        SQLiteDatabase database =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("uname", lb_name);
        cv.put("res", lb_res);

        long output =database.insert("Leaderboard", null, cv);
        if (output == -1)
            return false;
        else
            return true;
    }
}
