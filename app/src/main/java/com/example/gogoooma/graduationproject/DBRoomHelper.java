package com.example.gogoooma.graduationproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBRoomHelper extends SQLiteOpenHelper {
    private Context context;

    public DBRoomHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE TALKLIST ( ");
        sb.append(" DBNAME TEXT PRIMARY KEY, ");
        sb.append(" FRIENDNAME TEXT, ");
        sb.append(" LASTMSG TEXT, ");
        sb.append(" TIME INTEGER ) ");

        //SQLite Database로 쿼리실행
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addTalk(String dbname, String friendName, String lastmsg, long time){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TALKLIST ( ");
        sb.append(" DBNAME, FRIENDNAME, LASTMSG, TIME ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        dbname, friendName, lastmsg, time
                });
    }

    public List<Talk> getAllTalk(){
        List<Talk> talkList = new ArrayList();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DBNAME, FRIENDNAME, LASTMSG, TIME FROM TALKLIST " );
        sb.append(" ORDER BY TIME DESC " );
        // 읽기 전용 DB 객체를 만든다.
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sb.toString(), null);

        while( cursor.moveToNext() ) {
            talkList.add(new Talk(cursor.getString(0), cursor.getString(1),
                    cursor.getString(2), cursor.getLong(3)));
        }
        cursor.moveToFirst();
        cursor.close();

        return talkList;
    }

    public void updateTalk(String dbName, String data, long time){
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE  TALKLIST SET TIME = " + time);
        sb.append(" WHERE DBNAME = '" + dbName + "'");
        db.execSQL(sb.toString());

        sb = new StringBuffer();
        sb.append(" UPDATE  TALKLIST SET LASTMSG = '" + data + "'");
        sb.append(" WHERE DBNAME = '" + dbName + "'");
        db.execSQL(sb.toString());
    }
}
