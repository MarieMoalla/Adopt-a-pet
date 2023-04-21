package service;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.User;

public class DatabaseHelper extends SQLiteOpenHelper{

    //region constructor
    public DatabaseHelper(Context context){
        super(context ,DATABASE_NAME,null,DATABASE_VERSION );
    }
    //endregion

    //region interface method

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        Log.i("DataBase", "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // If table  already exist
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
        Log.i("DataBase", "Tables deleted");
    }

    //endregion

    //region datbase config

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "miniprojet.db";

    //endregion

    //region user table

    // tables names
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_LAST_NAME = "user_lastname";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";

    // create user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
            + USER_LAST_NAME + " TEXT," + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT"  +")";
    // drop table user sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //endregion

    //region user methods

    // add user to user table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getmName());
        values.put(USER_LAST_NAME, user.getmLastname());
        values.put(USER_EMAIL, user.getmEmail());
        values.put(USER_PASSWORD, user.getmPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // Void to check user exist in database
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {USER_ID};
        String selection = USER_EMAIL + "=?" + " and " + USER_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_USER, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0)return true;
        else return false;
    }

    // get user by email
    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {USER_ID,
                        USER_NAME, USER_LAST_NAME , USER_EMAIL , USER_PASSWORD }, USER_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();

        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
            user.setmName(cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME)));
            user.setmLastname(cursor.getString(cursor.getColumnIndexOrThrow(USER_LAST_NAME)));
            user.setmEmail(cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)));
            user.setmPassword(cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)));
        }
        return user;
    }
    //endregion
}
