package com.example.viewmodelex.Exercieses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DBFILE_CONTACE = "contact.db";

    public DatabaseHelper(Context context) {
        super(context, DBFILE_CONTACE, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactDBCtrct.SQL_CREATE_TBL);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//    INSERT OR REPLACE INTO CONTACT_T (EXER_NAME, EXER_CATE) VALUES (x, x)
    public void insert(String exerName, String exerCate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(ContactDBCtrct.SQL_INSERT +
                "( '" + exerName + "', '" + exerCate + "');");
        db.close();
    }

    public void delete(String exerName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(ContactDBCtrct.SQL_DELETE +
                " WHERE NAME = " + exerName + ";");
        db.close();
    }

    public ArrayList<ExerciesesItem> getResult() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<ExerciesesItem> datas = new ArrayList<>();

        Cursor cursor = db.rawQuery(ContactDBCtrct.SQL_SELECT, null);
        if(cursor.moveToFirst()) {
            do {
                ExerciesesItem item = new ExerciesesItem();
                item.setExerName(cursor.getString(cursor.getColumnIndex("NAME")));
                item.setCategory(cursor.getString(cursor.getColumnIndex("CATE")));

                datas.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return datas;
    }

}
