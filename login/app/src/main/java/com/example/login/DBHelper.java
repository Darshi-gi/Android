package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

    public class DBHelper extends SQLiteOpenHelper   {
        public static final String DATABASE_NAME= "user.db";
        public static final String TABLE_NAME= "user_table";
        public static final String COL_1 ="username";
        public static final String COL_2 ="password";
        public static final String COL_3 ="repassword";





        public DBHelper(Context context) {
            super(context,DATABASE_NAME,null,1);
            SQLiteDatabase myDB=this.getWritableDatabase();
        }


        @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table "+ TABLE_NAME +"(username Text,password Text,repassword Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("Drop Table if exists "+TABLE_NAME);
        onCreate(myDB);
    }

    public Boolean insertData(String username,String password){
        SQLiteDatabase myDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = myDB.insert("user",null,contentValues);

        if (result==-1){
            return false;
        }
        else {
           return true;
        }
    }

    public Boolean checkusername(String username){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select *from user where username =?",new String[]{username});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkusernamePassword(String username,String password){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select *from user where username =? and password =?",new String[]{username,password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
}
