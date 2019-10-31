package com.example.viewmodelex.WorkOut;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.viewmodelex.WorkOut.WorkOutItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class WorkOutDatabase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DBFILE_CONTACE = "workLog.db";

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
                        "WORK_DATE" + " DATETIME DEFAULT (datetime('now','+9 hours'))" +
                        ")"
        );
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //    INSERT OR REPLACE INTO CONTACT_T (EXER_NAME, EXER_CATE) VALUES (x, x)
    public void insert(HashMap<String, WorkOutItem> workMap) {
        SQLiteDatabase db = getWritableDatabase();

        for (String key : workMap.keySet()) {
            String title = key;
            WorkOutItem items = workMap.get(key);
            ArrayList kgList = items.getKgList();
            ArrayList repList = items.getRepList();
            for (int j = 0; j < kgList.size(); j++) {
                String workKg = kgList.get(j).toString();
                String workRep = repList.get(j).toString();
                String set = Integer.toString(j + 1);

                db.execSQL(
                        "INSERT OR REPLACE INTO WORK_LOG (WORK_TITLE, WORK_SET, WORK_KG, WORK_RAP)" +
                                " VALUES ( '" + title + "', '" + set + "', '" + workKg + "', '" + workRep + "' )"
                );

            }
        }
        db.close();
    }
//
//    public void delete(String exerName) {
//        SQLiteDatabase db = getWritableDatabase();
//        db.execSQL(ContactDBCtrct.SQL_DELETE +
//                " WHERE NAME = " + exerName + ";");
//        db.close();
//    }

    public HashMap<String, String> getWorkLog(String date) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> kgList = new ArrayList<>();
        ArrayList<String> repList = new ArrayList<>();
        HashMap<String, String> logData = new HashMap<>();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM WORK_LOG " +
                        "where WORK_DATE = '" + date + "';"
                , null);
        if (cursor.moveToFirst()) {
            do {

                titleList.add(cursor.getString(cursor.getColumnIndex("WORK_TITLE")));
                kgList.add(cursor.getString(cursor.getColumnIndex("WORK_KG")));
                repList.add(cursor.getString(cursor.getColumnIndex("WORK_RAP")));

            } while (cursor.moveToNext());
        }
        db.close();

        for (String title : titleList) {
            for (int i = 0; i < 5; i++) {
                logData.put(title, kgList.get(i));
                logData.put(title, repList.get(i));
            }
        }
        return logData;
    }
}
