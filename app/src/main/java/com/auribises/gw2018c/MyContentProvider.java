package com.auribises.gw2018c;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;


/*

Ishants-Macbook-Air:~ ishantkumar$ cd /Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ cd platform-tools/
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb root
adbd is already running as root
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.gw2018c/databases
generic_x86:/data/data/com.auribises.gw2018c/databases # ls
Customers.db
generic_x86:/data/data/com.auribises.gw2018c/databases # sqlite3 Customers.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
Customer          android_metadata
sqlite> select * from Customer;
1|John Watson|+91 99999 88888|john@example.com
2|Jenni|+91 99999 77777|jennie@example.com
3|Jack|+91 88888 77777|jack@example.com
sqlite>

 */

public class MyContentProvider extends ContentProvider {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       String tabName = uri.getLastPathSegment();
       int i = sqLiteDatabase.delete(tabName,selection,null);
       return i;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tabName = uri.getLastPathSegment();
        long id = sqLiteDatabase.insert(tabName,null,values);
        Uri uri1 = Uri.parse("dummy://anything/"+id);
        return uri1;
    }

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext(),Util.DB_NAME,null,Util.DB_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String tabName = uri.getLastPathSegment();
        Cursor cursor = sqLiteDatabase.query(tabName,projection,null,null,null,null,null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version); // Create the DataBase
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Util.CREATE_TAB_QUERY);      // Create the Table
            //db.execSQL(Util.CREATE_TAB_QUERY1);
            //db.execSQL(Util.CREATE_TAB_QUERY2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if(oldVersion != newVersion){
                //..
                //db.execSQL();
            }
        }
    }
}
