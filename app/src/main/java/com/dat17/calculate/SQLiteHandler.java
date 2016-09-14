package com.dat17.calculate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Alone on 9/14/2016.
 */
public class SQLiteHandler extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private Context dbContext;
    private static final String DB_PATH = "/data/data/com.dat17.calculate/databases/";
    private static final String DB_NAME = "db2016.sqlite";
    private static final int DB_VERSION = 1;
    private final String PATH = DB_PATH + DB_NAME;

    public SQLiteHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.dbContext = context;
        openDB();
    }


    private void openDB() {
        if (checkDB()) {
            db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } else {
            copyDBToSDCard();
            Toast.makeText(dbContext, "Copy database to SDCard", Toast.LENGTH_LONG).show();
        }
    }
    public void execute(String sql) {
        db.execSQL(sql);
    }

    public Cursor rawQuery(String sql) {
        Cursor c;
        c = db.rawQuery(sql, null);
        return c;
    }

    private boolean checkDB() {
        try {
            File file = new File(PATH);
            if (file.exists()) return true;
        } catch (Exception io) {
            io.printStackTrace();
            return false;
        }
        return false;
    }

    private void copyDBToSDCard() {
        this.getReadableDatabase();
        try {
            InputStream inputStream = dbContext.getAssets().open(DB_NAME);
            File file = new File(PATH);
            OutputStream outputStream = new FileOutputStream(PATH);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
