package util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Closeable;

public class DatabaseOpenHelper extends SQLiteOpenHelper implements Closeable{

    public static final String DATABASE_NAME = "ice1000.db" ;
    public static final String GROUP = "names" ;
    public static final String TABLE_NAME = "ice1000" ;
    public static final int DATABASE_VERSION = 1 ;

    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + GROUP + "(name TEXT PRIMARY KEY);");
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
//                "(useless INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, id INTEGER, time INTEGER, cnt INTEGER);");
    }

    public void createTable(SQLiteDatabase db,String name){
        db.execSQL("CREATE TABLE IF NOT EXISTS " + name +
                "(name TEXT, id INTEGER, time INTEGER, cnt INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE ice1000 ADD COLUMN other STRING");
        db.execSQL("ALTER TABLE names ADD COLUMN other STRING");
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
