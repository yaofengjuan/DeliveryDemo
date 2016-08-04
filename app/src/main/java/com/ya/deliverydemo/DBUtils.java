package com.ya.deliverydemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YaoFengjuan on 2016/6/24.
 */
public class DBUtils extends SQLiteOpenHelper {

    private static final int _Version = 1;
    private Context context;


    public DBUtils(Context context, String name) {
        super(context, name, null, _Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists myExp (_id integer primary key autoincrement,"
                + "expSpellName text,"
                + "mailNo text,"
                + "simpleName text,"
                + "logoUrl,text"
                + "lastMsg,text"
                + "remarkName,text"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
