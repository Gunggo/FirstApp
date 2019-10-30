package com.example.viewmodelex.Exercieses;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.viewmodelex.WorkOut.WorkOutItem;

import java.util.ArrayList;
import java.util.List;

public class WorkOutDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DBFILE_CONTACE = "contact.db";

    public WorkOutDatabase(Context context) {
        super(context, DBFILE_CONTACE, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS "
                        + "WORK_LOG " +
                        "(" +
                        "WORK_TITLE" + " TEXT" + ", " +
                        "WORK_SET" + " TEXT" + ", " +
                        "WORK_KG" + " TEXT" + ", " +
                        "WORK_RAP" + " TEXT" + ", " +
                        "WORK_DATE" + " TEXT"        +
                        ")"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //    INSERT OR REPLACE INTO CONTACT_T (EXER_NAME, EXER_CATE) VALUES (x, x)
    public void insert(String title, String set, String kg, String rap) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(
                "INSERT OR REPLACE INTO CONTACT_T (WORK_TITLE, WORK_SET, WORK_KG, WORK_RAP, WORK_DATE)" +
                        " VALUES ( " + title + set + kg + rap + "DATETIME('NOW','+9 HOURS') )"
        );
        db.close();
    }
//
//    public void delete(String exerName) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL(ContactDBCtrct.SQL_DELETE +
//                " WHERE NAME = " + exerName + ";");
//        db.close();
//    }

    public List<WorkOutItem> getWorkLog() {
        SQLiteDatabase db = getReadableDatabase();
        List<WorkOutItem> datas = new ArrayList<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM WORK_LOG"
                , null);
        if(cursor.moveToFirst()) {
            do {
                WorkOutItem item = new WorkOutItem();
                item.setTitle(cursor.getString(cursor.getColumnIndex("WORK_TITLE")));
                item.setSet(cursor.getString(cursor.getColumnIndex("WORK_SET")));
                item.setKilogram(cursor.getString(cursor.getColumnIndex("WORK_KG")));
                item.setRep(cursor.getString(cursor.getColumnIndex("WORK_RAP")));
                item.setDate(cursor.getString(cursor.getColumnIndex("WORK_DATE")));

                datas.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return datas;
    }

}
