package com.example.viewmodelex.Exercieses;

public class ContactDBCtrct {

    public static final String TBL_CONTACT = "CONTACT_T";
//    public static final String EXER_NO = "NO";
    public static final String EXER_NAME = "NAME";
    public static final String EXER_CATE = "CATE";

    public static final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS "
            + TBL_CONTACT + " " +
            "(" +
//                EXER_NO + " INTEGER NOT NULL" + ", " +
                EXER_NAME + " TEXT" + ", " +
                EXER_CATE + " TEXT"        +
            ")";

    public static final String SQL_DROP_TBL = "DROP TABLE IF EXISTS " + TBL_CONTACT;

    public static final String SQL_SELECT = "SELECT * FROM " + TBL_CONTACT;

    // INSERT OR REPLACE INTO CONTACT_T (EXER_NAME, EXER_CATE) VALUES (x, x, x)
    public static final String SQL_INSERT = "INSERT OR REPLACE INTO " + TBL_CONTACT + " " +
            "(" + EXER_NAME + ", " + EXER_CATE + ") VALUES ";

    public static final String SQL_DELETE = "DELETE FROM " + TBL_CONTACT;


}
