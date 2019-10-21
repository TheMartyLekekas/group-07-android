package se.chalmers.cse.dit341.group07;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    public static final String TABLE_NAME = "product";
    public static final String COL1 = "ID";
    public static final String COL2 = "NAME";
    public static final String COL3 = "DESCRIPTION";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID TEXT PRIMARY KEY, " + "NAME TEXT, DESCRIPTION TEXT)";
        db.execSQL(createTable);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProduct(String id, String name, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public Integer deleteProduct(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete("products", "id=?", new String[]{Integer.toString(id)});
    }
    public void delete(String id)
    {
        String[] args={id};
        getWritableDatabase().delete("products", "_ID=?", args);
    }
    public void deleteAllProducts()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);
        db.execSQL(" DELETE FROM " + TABLE_NAME);
        db.execSQL("vacuum"); //clear all allocated spaces
        db.close();
    }

}
