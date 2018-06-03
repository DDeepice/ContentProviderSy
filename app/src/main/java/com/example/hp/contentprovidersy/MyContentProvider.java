package com.example.hp.contentprovidersy;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    public static final int CONTACTS_DIR = 0;

    public static final int CONTACTS_ITEM = 1;

    public static final String AUTHORITY = "com.example.hp.contentProviderSy";

    private static UriMatcher uriMatcher;

    private MyDataBaseHelper dbHelper;

    static {

        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //访问所有数据
        uriMatcher.addURI(AUTHORITY,"contacts",CONTACTS_DIR);

        //访问单条数据
        uriMatcher.addURI(AUTHORITY,"contacts/#",CONTACTS_ITEM);



    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:return "vnd:android.cursor.dir/vnd.com.example.hp.contentProviderSy.contacts";
            case CONTACTS_ITEM:return "vnd:android.cursor.item/vnd.com.example.hp.contentProviderSy.contacts";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDataBaseHelper(getContext(),"Content.db",null,2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
           switch (uriMatcher.match(uri)){
               case CONTACTS_DIR:
                   cursor = db.query("Contacts",projection,selection,selectionArgs,null,null,sortOrder);
                   break;
               case CONTACTS_ITEM:
                   String contactId = uri.getPathSegments().get(1);
                   cursor = db.query("Contacts",projection,"id=?",new String[]{contactId},null,null,sortOrder);
                   break;
               default:
                   break;

           }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
