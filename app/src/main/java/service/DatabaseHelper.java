package service;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import model.Pet;
import model.User;

public class DatabaseHelper extends SQLiteOpenHelper{

    //region constructor
    private Context mContext;

    //region datbase config

    // Database Version
    private static final int DATABASE_VERSION = 16;
    // Database Name
    private static final String DATABASE_NAME = "miniprojet.db";

    //endregion

    public DatabaseHelper(Context context){
        super(context ,DATABASE_NAME,null,DATABASE_VERSION );
        mContext=context;
    }

    //endregion

    //region interface method

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

    //region USER

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

    //region  get current user id
    public int currentUserId() {
        SharedPreferences sp = mContext.getSharedPreferences("Login", Context.MODE_PRIVATE);
        return sp.getInt("Id",0);
    }
    //endregion

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

    // region  to check user exist in database
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

    //region   to check user email
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

    //#region update user
    public boolean updateUser(int userId,User user )
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if(user.getmName() != "" && user.getmName() != null)  values.put(USER_NAME, user.getmName());
            if(user.getmLastname() != "" && user.getmLastname() != null) values.put(USER_LAST_NAME, user.getmLastname());
            if(user.getmEmail() != "" && user.getmEmail() != null) values.put(USER_EMAIL, user.getmEmail());
            if(user.getmPassword() != "" && user.getmPassword() != null) values.put(USER_PASSWORD, user.getmPassword());

            int result = db.update(TABLE_USER, values, USER_ID + " = ?",
                    new String[]{String.valueOf(userId)});
            db.close();
            return result != 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //#endregion

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

    //#region retrive user password
    public String getUserPassword(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {USER_PASSWORD}, USER_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        String pwd ="";

        if(cursor.moveToFirst()){
            pwd = cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD));
        }
        return pwd;
    }
    //#endregion

    //#region delete user
    public long deleteUserById(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long deletedId = db.delete(TABLE_USER, USER_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return deletedId;
    }

    //#endregion
    //endregion

    //region PET

    //region pet table

    // tables pet
    private static final String TABLE_PET = "pet";
    private static final String PET_ID = "pet_id";
    private static final String PET_NAME = "pet_name";
    private static final String PETBREED = "pet_breed";
    private static final String PETGENDER = "pet_gender";
    private static final String PETWEIGHT = "pet_weight";

    private static final String PETAGE = "pet_age";
    private static final String PETHASREPORT = "pet_hasreport";
    private static final String PETFORADAPTION = "pet_foradoption";
    private static final String PETOWNERID = "pet_ownerid";


    // create pet table sql query
    private String CREATE_PET_TABLE = "CREATE TABLE " + TABLE_PET + "("
            + PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PET_NAME + " TEXT,"
            + PETBREED + " TEXT," + PETGENDER + " TEXT,"
            + PETWEIGHT + " REAL," + PETAGE + " INTEGER, "  + PETHASREPORT +" INTEGER," + PETFORADAPTION +" INTEGER,"+ PETOWNERID +" INTEGER, "
            + " FOREIGN KEY ("+PETOWNERID+") REFERENCES "+TABLE_USER+"("+USER_ID+"));";

    // drop table pet sql query
    private String DROP_PET_TABLE = "DROP TABLE IF EXISTS " + TABLE_PET;

    //endregion

    //region pet methods

    //region Create Pet
    public void addPet(Pet pet) {
        Log.i("value pet",String.valueOf(pet.getName()));
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PET_NAME, pet.getName());
        values.put(PETBREED, pet.getBreed());
        values.put(PETWEIGHT, pet.getWeight());
        values.put(PETGENDER, pet.getGender());
        values.put(PETFORADAPTION, pet.getForAdaption());
        values.put(PETAGE, pet.getAge());
        values.put(PETHASREPORT, pet.getHasReport());
        values.put(PETOWNERID, pet.getOwnerId());

        // Inserting Row
        try{
            Log.i("pets values",values.toString());
            db.insert(TABLE_PET, null, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        db.close();
    }
    //endregion

    //region get my pets

    public ArrayList<Pet> getMyPets(int ownerId) {
        ArrayList<Pet> petsList = new ArrayList<Pet>();

        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PET;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if(Integer.parseInt(cursor.getString(7)) == ownerId)
                {
                    Pet pet = new Pet();
                    pet.setId(Integer.parseInt(cursor.getString(0)));
                    pet.setName(cursor.getString(1));
                    pet.setBreed(cursor.getString(2));
                    pet.setGender(cursor.getString(3));
                    pet.setWeight(cursor.getFloat(4));
                    pet.setHasReport(Integer.parseInt(cursor.getString(5)));
                    pet.setForAdaption(Integer.parseInt(cursor.getString(6)));
                    pet.setOwnerId(Integer.parseInt(cursor.getString(7)));
                    petsList.add(pet);
                }
            } while (cursor.moveToNext());
        }
        db.close();
        return petsList;
    }

    //endregion

    //region update pet
    public boolean updatePet(int petid, String name, String breed, String gender, float weight, int age)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if(name != "" && name != null)  values.put(PET_NAME, name);
            if(breed != "" && breed != null) values.put(PETBREED, breed);
            if(gender != "" && gender != null) values.put(PETGENDER, gender);
            if(weight != 0f) values.put(PETWEIGHT, weight);
            if(age != 0) values.put(PETAGE, age);

            int result = db.update(TABLE_PET, values, PET_ID + " = ?",
                    new String[]{String.valueOf(petid)});
            db.close();
            return result != 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //#endregion

    //region get all pets for adaption

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> petsList = new ArrayList<Pet>();

        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PET;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if(Integer.parseInt(cursor.getString(7)) == 1 )
                {
                    Pet pet = new Pet();
                    pet.setId(Integer.parseInt(cursor.getString(0)));
                    pet.setName(cursor.getString(1));
                    pet.setBreed(cursor.getString(2));
                    pet.setGender(cursor.getString(3));
                    pet.setWeight(cursor.getFloat(4));
                    pet.setHasReport(Integer.parseInt(cursor.getString(5)));
                    pet.setForAdaption(Integer.parseInt(cursor.getString(6)));
                    pet.setOwnerId(Integer.parseInt(cursor.getString(7)));
                    petsList.add(pet);
                }

            } while (cursor.moveToNext());
        }
        db.close();
        return petsList;
    }

    //endregion

    //region get pet detail
    public Pet getpetDetail(int petid) {


        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PET + " WHERE pet_id = "+ petid;


        try
        {
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor != null ) cursor.moveToFirst();
            Pet p = new Pet(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    Float.parseFloat(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    Integer.parseInt(cursor.getString(6)),
                    Integer.parseInt(cursor.getString(7)),
                    Integer.parseInt(cursor.getString(8)));
            db.close();
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            + REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + REQUEST_ACCEPTED + " INTEGER,"
            + REQUEST_OWNERID + " INTEGER,"
            + REQUEST_ADAPTERID + " INTEGER,"
            + REQUEST_PETID + " INTEGER,"
            + "FOREIGN KEY ("+ REQUEST_PETID +") REFERENCES "+ TABLE_PET+ "("+PET_ID+"))";

    // drop table user sql query
    private String DROP_REQUEST_TABLE = "DROP TABLE IF EXISTS " + TABLE_REQUEST;

    //endregion


}
