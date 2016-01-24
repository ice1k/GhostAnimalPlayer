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
import static util.DatabaseOpenHelper.*;

/**
 * 封装SQLite操作。
 * Created by Administrator on 2015/12/14 0014.
 */
public class DatabaseManager implements Closeable{

    private DatabaseOpenHelper helper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        onCreate(context);
    }

    private void onCreate(Context context){
        helper = new DatabaseOpenHelper(context);
//        可读可写
        database = helper.getWritableDatabase();
//        helper.close();
    }


//    增加一组声音
    public void addSounds(String groupName, List<OneSound> sounds) {
        database.beginTransaction();
        try{
            for (OneSound sound:sounds) {
                ContentValues contentValues = new ContentValues();
//                contentValues.put("name", sound.name);
                contentValues.put("id", sound.id);
                contentValues.put("time", sound.time);
                contentValues.put("cnt", sound.cnt);
                database.insert(groupName, null, contentValues);
//                database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
//                        TABLE_NAME, sound.name, sound.id, sound.time, i));
            }
            database.setTransactionSuccessful();
//            database.endTransaction();
        }
        finally {
            database.endTransaction();
        }
    }

    public void addSound(String name, OneSound sound) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", sound.name);
        contentValues.put("id", sound.id);
        contentValues.put("time", sound.time);
        contentValues.put("cnt", sound.cnt);
        database.insert(name, null, contentValues);
//        database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
//                TABLE_NAME, sound.name, sound.id, sound.time, sound.cnt));

    }

    // 听说少产生一些对象会减少内存消耗？
    public void addSound(String name, int id, long time, int cnt) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
        contentValues.put("id", id);
        contentValues.put("time", time);
        contentValues.put("cnt", cnt);
        database.insert(name, null, contentValues);
//        database.execSQL(String.format("INSERT INTO '%s' VALUES('%s',%d,%d,%d)",
//                TABLE_NAME, name, id, time, cnt));

    }

    public String findSoundGroupName(){
//        Cursor cursor = database.query(GROUP, null, null, null, null, null, null);
        int i;
        String name ;
//        boolean bool = true;
//        while(bool){
        i = (int) (Math.random()*1000000);
        name = String.format("%8s%d", JSON, i);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        helper.createTable(database, name);
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
        database.delete(name, null, null);
        database.execSQL("DROP TABLE " + name);
        database.delete(GROUP, "name = ?", new String[]{name});
    }

    public void renameSoundGroup(String oldName,String newName){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",newName);
        // 万恶的where表达式
        database.update(GROUP, contentValues, "name = ?", new String[]{oldName});
        database.execSQL("ALTER TABLE " + oldName + " RENAME TO " + newName);
    }

    public ArrayList<OneSound> querySounds(String name){
        ArrayList<OneSound> oneSoundArrayList = new ArrayList<>();
//        Cursor cursor = database.query(TABLE_NAME,
//                new String[]{"id", "time"}, "name = ?", new String[]{name}, null, null, "cnt");
        Cursor cursor = database.query(name,
                null, null,
                null, null,
                null, "cnt");
        while(cursor.moveToNext()){
            Log.d(MY_TAG, "find one sound!!Yeah!!");
            oneSoundArrayList.add(new OneSound(
//                    cursor.getString(cursor.getColumnIndex("name")),
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
        Cursor cursor = database.query(GROUP,
                null, null,
                null, null,
                null, null);
        while(cursor.moveToNext()){
            Log.d(MY_TAG,"cursor.getString(cursor.getColumnIndex(\"name\")) = "
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
    }
}
