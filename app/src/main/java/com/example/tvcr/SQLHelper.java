package com.example.tvcr;

//This class is not an Activity. It is a helper class
// used to execute the SQL statements on SQLite.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contact.db";
    public static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "contactInfo";
    public static final String KEY_NAME = "name";
    public static final String KEY_N = "number";
//    public static final String KEY_E = "email";
    public static final String KEY_U = "url";
    public static final String KEY_A = "address";
    public static final String KEY_ID = "id integer primary key autoincrement";
    public static final String CREATE_TABLE = "CREATE TABLE contactInfo ("
            + KEY_ID + "," + KEY_NAME + " text,"
            + KEY_N + " text,"
            + KEY_U + " text," + KEY_A
            //+ " text" + KEY_E
            + " text,);";

    private ContentValues values;
    private ArrayList<ContactInfo> contactList;
    private Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //called to create table
    //NB: this is not a lifecycle method because this class is not an Activity
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = CREATE_TABLE;
        Log.d("SQLiteDemo", "onCreate: " + sql);
        db.execSQL(sql);
    }

    //called when database version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;

        Log.d("SQLiteDemo", "onUpgrade: Version = " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);   //not calling a lifecycle method
    }

    //add animal to database
    public void addAnimal(ContactInfo item) {
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_N, item.getNumber());
//        values.put(KEY_E, item.getEmail());
        values.put(KEY_U, item.getUrl());
        values.put(KEY_A, item.getAddress());
        db.insert(TABLE_NAME, null, values);
        Log.d("SQLiteDemo", item.getName() + " added");
        db.close();
    }

    // TODO do more than update names?
    //update Animal name in database
    public void updateAnimal(ContactInfo item, ContactInfo newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_NAME, newItem.getName());
        db.update(TABLE_NAME, values, KEY_NAME + "=?", new String[] {item.getName()});
        Log.d("SQLiteDemo", item.getName() + " updated");
        db.close();
    }

    //delete animal from database
    public void deleteAnimal(ContactInfo item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + "=?", new String[] {item.getName()});
        Log.d("SQLiteDemo", item.getName() + " deleted");
        db.close();
    }

    //query database and return ArrayList of all animals
    public ArrayList<ContactInfo> getContactList () {

        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(TABLE_NAME,
                new String[] {KEY_NAME, KEY_N,
                        KEY_U, KEY_A,
                        //KEY_E
                        },
                null, null, null, null, KEY_NAME);

        //write contents of Cursor to list
        contactList = new ArrayList<ContactInfo>();
        while (cursor.moveToNext()) {
            String strName = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(KEY_N));
//            String strEmail = cursor.getString(cursor.getColumnIndex(KEY_E));
            String strUrl = cursor.getString(cursor.getColumnIndex(KEY_U));
            String strAddress = cursor.getString(cursor.getColumnIndex(KEY_A));
//            contactList.add(new ContactInfo(strName, phoneNumber, strEmail, strUrl, strAddress));
            contactList.add(new ContactInfo(strName, phoneNumber, strUrl, strAddress));
        };
        db.close();
        return contactList;

    }
}


