package service;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import model.Pet;
import model.User;

public class DatabaseHelper extends SQLiteOpenHelper{

    //region constructor

    private Context mContext;

    public DatabaseHelper(Context context){
        super(context ,DATABASE_NAME,null,DATABASE_VERSION );
        mContext=context;
    }

    //endregion

    //region interface method

    //get current user id
    public int currentUserId() {
        SharedPreferences sp = mContext.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return sp.getInt("Id",0);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_PET_TABLE);
        sqLiteDatabase.execSQL(CREATE_REQUEST_TABLE);
        Log.i("DataBase", "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // If table  already exist
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(DROP_PET_TABLE);
        sqLiteDatabase.execSQL(DROP_REQUEST_TABLE);
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

    // tables User
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_LAST_NAME = "user_lastname";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";

    private static final String USER_PWDTOKEN = "user_pwd_teken";


    // create user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
            + USER_LAST_NAME + " TEXT," + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT,"  + USER_PWDTOKEN +" TEXT" +")";
    // drop table user sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //endregion

    //region user methods

    //region add user to user table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getmName());
        values.put(USER_LAST_NAME, user.getmLastname());
        values.put(USER_EMAIL, user.getmEmail());
        values.put(USER_PASSWORD, user.getmPassword());
        values.put(USER_PWDTOKEN, user.getPwdToken());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    //endregion

    // region Void to check user exist in database
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
    //endregion

    //region  Void to check user exist in database
    public boolean checkUserEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {USER_ID};
        String selection = USER_EMAIL + "=?" ;
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USER, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0)return true;
        else return false;
    }
    //endregion

    //region get user by email
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

    //region get user id
    public int getUserId(String email) {
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
        return user.getId();
    }
    //endregion
    //endregion

    //region pet table

    // tables pet
    private static final String TABLE_PET = "pet";
    private static final String PET_ID = "pet_id";
    private static final String PET_NAME = "pet_name";
    private static final String PETBREED = "user_lastname";
    private static final String PETGENDER = "user_email";
    private static final String PETWEIGHT = "user_password";
    private static final String PETHASREPORT = "user_password";
    private static final String PETFORADAPTION = "user_password";
    private static final String PETOWNERID = "user_pwd_teken";


    // create pet table sql query
    private String CREATE_PET_TABLE = "CREATE TABLE " + TABLE_PET + "("
            + PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PET_NAME + " TEXT,"
            + PETBREED + " TEXT," + PETGENDER + " TEXT,"
            + PETWEIGHT + " REAL,"  + PETHASREPORT +" INTEGER," + PETFORADAPTION +" INTEGER, CONSTRAINT fk_owner FOREIGN KEY ("+ PETOWNERID +") REFERENCES "+ TABLE_USER+ "("+USER_ID+"))";
    // drop table pet sql query
    private String DROP_PET_TABLE = "DROP TABLE IF EXISTS " + TABLE_REQUEST;

    //endregion

    //region pet methods

    //region Create Pet
    public void addPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PET_NAME, pet.getName());
        values.put(PETBREED, pet.getBreed());
        values.put(PETWEIGHT, pet.getWeight());
        values.put(PETGENDER, pet.getGender());
        values.put(PETFORADAPTION, pet.getForAdaption());
        values.put(PETHASREPORT, pet.getHasReport());
        values.put(PETOWNERID, pet.getOwnerId());

        // Inserting Row
        db.insert(TABLE_PET, null, values);
        db.close();
    }
    //endregion

    //region get my pets

    public List<Pet> getMyPets() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PET, new String[] { PET_ID , PET_NAME ,PETBREED , PETGENDER ,PETWEIGHT,PETHASREPORT , PETFORADAPTION, PETOWNERID},
                PETOWNERID + "=?",
                new String[] { String.valueOf(this.currentUserId()) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        List<Pet> pets = new ArrayList<Pet>();

        while(!cursor.isAfterLast())
        {
            Pet p = new Pet();
            p.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PET_ID)));

            cursor.moveToNext();
        }
        return pets;
    }
    //endregion

    //endregion

    //region request table

    // tables request
    private static final String TABLE_REQUEST = "request";
    // User Table Columns names
    private static final String REQUEST_ID = "request_id";
    private static final String REQUEST_ACCEPTED = "request_accepted";
    private static final String REQUEST_OWNERID = "request_ownerid";
    private static final String REQUEST_ADAPTERID = "request_adapterid";
    private static final String REQUEST_PETID = "request_petid";

    // create request table sql query
    private String CREATE_REQUEST_TABLE = "CREATE TABLE " + TABLE_REQUEST + "("
            + REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + REQUEST_ACCEPTED + " INTEGER,"
            + REQUEST_OWNERID + " INTEGER," + REQUEST_ADAPTERID + " INTEGER, CONSTRAINT fk_pet FOREIGN KEY ("+ REQUEST_PETID +") REFERENCES "+ TABLE_PET+ "("+PET_ID+"))";
    // drop table user sql query
    private String DROP_REQUEST_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    //endregion


}
