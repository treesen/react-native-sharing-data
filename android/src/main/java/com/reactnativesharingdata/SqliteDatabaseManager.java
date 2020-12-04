package com.reactnativesharingdata;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDatabaseManager extends SQLiteOpenHelper{

    public SqliteDatabaseManager(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String	CREATE_PHONEBOOK_TABLE = "CREATE	TABLE " + Constants.Content.TABLE_NAME + "(" + Constants.Content.ID + " INTEGER PRIMARY KEY,"
                + Constants.Content.CONTENT_KEY + " TEXT," + Constants.Content.CONTENT_VALUE + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_PHONEBOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.Content.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
