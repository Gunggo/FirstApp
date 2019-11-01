package com.example.viewmodelex.WorkOut;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<String, WorkOutItem> getWorkLog(String date) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> kgList = new ArrayList<>();
        ArrayList<String> repList = new ArrayList<>();
        HashMap<String, WorkOutItem> logData = new HashMap<>();

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

//        for (String title : titleList) {
//            logData.put(title, kgList);
//            logData.put(title, repList);
//        }
//        mapData = new HashMap<>();
//        workData = new ArrayList<>();
//
//        for (int i = 0; i < titleList.size(); i++) {
//            WorkOutItem workOutItem = new WorkOutItem();
//            workOutItem.addKgList(holderList.get(i).etKg1.getText().toString());
//            workOutItem.addKgList(holderList.get(i).etKg2.getText().toString());
//            workOutItem.addKgList(holderList.get(i).etKg3.getText().toString());
//            workOutItem.addKgList(holderList.get(i).etKg4.getText().toString());
//            workOutItem.addKgList(holderList.get(i).etKg5.getText().toString());
//
//            workOutItem.addRepList(holderList.get(i).etRep1.getText().toString());
//            workOutItem.addRepList(holderList.get(i).etRep2.getText().toString());
//            workOutItem.addRepList(holderList.get(i).etRep3.getText().toString());
//            workOutItem.addRepList(holderList.get(i).etRep4.getText().toString());
//            workOutItem.addRepList(holderList.get(i).etRep5.getText().toString());
//
//            mapData.put(titleList.get(i),workOutItem);
//        }
//
//        return mapData;
        return logData;
    }
}
