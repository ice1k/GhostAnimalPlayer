package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import static util.Constants.JSON;
import static util.Constants.MY_TAG;
import static util.DatabaseOperator.*;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class DatabaseManager implements Closeable{

    private DatabaseOperator helper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        onCreate(context);
    }

    private void onCreate(Context context){
        helper = new DatabaseOperator(context);
//        可读可写
        database = helper.getWritableDatabase();
//        helper.close();
    }

//    增加一组声音
    public void addSounds(List<OneSound> sounds) {
        database.beginTransaction();
        try{
            for (int i = 0; i < sounds.size(); i++) {
                OneSound sound = sounds.get(i);
                database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
                        TABLE_NAME, sound.name, sound.id, sound.time, i));
            }
            database.setTransactionSuccessful();
//            database.endTransaction();
        }
        finally {
            database.endTransaction();
        }
    }

    public void addSound(OneSound sound) {
        database.beginTransaction();
        try{
            database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
                    TABLE_NAME, sound.name, sound.id, sound.time, sound.cnt));
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    // 听说少产生一些对象会减少内存消耗？
    public void addSound(String name, int id, long time, int cnt) {
        database.beginTransaction();
        try{
            database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
                    TABLE_NAME, name, id, time, cnt));
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public String findSoundGroupName(){
//        Cursor cursor = database.query(GROUP, null, null, null, null, null, null);
        double i;
        String name ;
//        boolean bool = true;
//        while(bool){
        i = Math.random();
        name = String.format("%8s%s", i, JSON);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        database.insert(GROUP, null, contentValues);
//            while(cursor.moveToNext()){
//                if(cursor.getString(cursor.getColumnIndex("name")).equals(name)) {
//                    i = Math.random();
//                    bool = false;
//                    break;
//                }
//            }
//        }
        return name;
    }

    public void deleteSoundGroup(String name){
        // 万恶的where表达式
        database.delete(TABLE_NAME, "name = ?", new String[]{name});
        database.delete(GROUP, "name = ?", new String[]{name});
    }

    public void renameSoundGroup(String oldName,String newName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(oldName,newName);
        // 万恶的where表达式
        database.update(TABLE_NAME, contentValues, "name = ?", new String[]{oldName});
        database.update(GROUP, contentValues,"name = ?", new String[]{oldName});
    }

    public ArrayList<OneSound> querySounds(String name){
        ArrayList<OneSound> oneSoundArrayList = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME,
                null, "name = ?", new String[]{name}, null, null, "cnt");
        while(cursor.moveToNext()){
            oneSoundArrayList.add(new OneSound(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getLong(cursor.getColumnIndex("time"))
                    // sound的顺序已经由游标本身确定了，所以不用构造
            ));
        }
        cursor.close();
        return oneSoundArrayList;
    }

    public String[] queryGroups(){
        ArrayList<String> namesList = new ArrayList<>();
        String[] names;
        Cursor cursor = database.query(GROUP, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Log.d(MY_TAG,"cursor.getString(cursor.getColumnIndex(\"name\"))"
                    + cursor.getString(cursor.getColumnIndex("name")));
            namesList.add(cursor.getString(cursor.getColumnIndex("name")));
        }
        cursor.close();
        names = new String[namesList.size()];
        for(int i = 0; i < namesList.size(); i++){
            names[i] = namesList.get(i);
        }
        return names;
    }

    private void onDestroy(){
        database.close();
        helper.close();
    }

    @Override
    public void close(){
        onDestroy();
        this.close();
    }
}
