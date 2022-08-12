package com.diazna.simptacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String NAMA_DB = "simptact.db";
    private static final int DATABASE_VERSION = 1;

    private static final String NAMA_TABLE = "kontak";
    private static final String KOLOM_ID = "id";
    private static final String KOLOM_NAMA = "nama";
    private static final String KOLOM_NOTELP = "notelp";
    private static final String KOLOM_EMAIL = "email";

    dbHelper(@Nullable Context context) {
        super(context, NAMA_DB, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + NAMA_TABLE
                        + " ( " + KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + KOLOM_NAMA + " TEXT,"
                        + KOLOM_NOTELP + " TEXT, "
                        + KOLOM_EMAIL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + NAMA_TABLE);
        onCreate(db);
    }

    void addContact(String nama, String noTelp, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KOLOM_NAMA, nama);
        cv.put(KOLOM_NOTELP, noTelp);
        cv.put(KOLOM_EMAIL, email);
        long result = db.insert(NAMA_TABLE, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Gagal Menambah Kontak", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + NAMA_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String nama, String noTelp, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KOLOM_NAMA, nama);
        cv.put(KOLOM_NOTELP, noTelp);
        cv.put(KOLOM_EMAIL, email);

        long result = db.update(NAMA_TABLE, cv, "id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Gagal Memperbarui Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Berhasil Memperbarui Data", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteSatuData(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(NAMA_TABLE, "id=?", new String[]{row_id});
        if(result == -1)
        {
            Toast.makeText(context, "Gagal Menghapus", Toast.LENGTH_SHORT).show();;
        }
        else
        {
            Toast.makeText(context, "Berhasil Menghapus", Toast.LENGTH_SHORT).show();;
        }

    }
}
