package ru.maryonegames.android.aliasgame;

/**
 * Created by vamar on 03.01.2018.
 */

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "words1.db";
    private static final int SCHEMA = 3; // версия базы данных
    static final String TABLE_BASE = "base"; // название таблицы в бд
    static final String TABLE_NEW_YEAR = "new_year"; // название таблицы в бд
    static final String TABLE_STARWAR = "star_wars"; // название таблицы в бд
    static final String TABLE_FILM = "film"; // название таблицы в бд
    static final String TABLE_FAMOUS = "famous"; // название таблицы в бд
    static final String TABLE_MIDDLE = "middle"; // название таблицы в бд
    static final String TABLE_VALEN = "valen"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_ID = "_id";
    static final String COLUMN_RU = "word_ru";
    static final String COLUMN_EN = "word_en";
    static final String COLUMN_JP = "word_jp";
    static final String COLUMN_UK = "word_uk";

    private Context myContext;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() +"/"+ DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

    }

    void create_db(){


        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
