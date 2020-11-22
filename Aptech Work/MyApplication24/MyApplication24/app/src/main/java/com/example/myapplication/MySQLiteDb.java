package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MySQLiteDb extends SQLiteOpenHelper {

    public MySQLiteDb(Context context) {
        super(context, "StudentDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Student(Id integer primary key autoincrement,Name text,Phone text,Email text,Password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(String name,String phone, String email,String password)
    {
        SQLiteDatabase db=   getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Name",name);
        values.put("Phone",phone);
        values.put("Email",email);
        values.put("Password",password);

        long res= db.insert("Student",null,values);
        return  res!=-1;

    }
    public boolean delete(String id)
    {
        SQLiteDatabase db=   getWritableDatabase();
        int res= db.delete("Student","Id=?",new String[]{id});

        return  res==1;
    }

    public Cursor  fetch()
    {
        SQLiteDatabase db=   getWritableDatabase();
       Cursor cursor= db.rawQuery("select * from Student",null);

       return  cursor;
    }
    public Cursor  fetch(String id)
    {
        SQLiteDatabase db=   getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Student where Id = '"+id+"'",null);

        return  cursor;
    }

    public ArrayList<Student> fetchStudents()
    {
        ArrayList<Student> lst=new ArrayList<>();
        SQLiteDatabase db=   getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Student",null);
        while (cursor.moveToNext())
        {
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            String phone=cursor.getString(2);
            String email=cursor.getString(3);
            String pass=cursor.getString(4);

            lst.add(new Student(id,name,phone,email,pass));

        }
        return  lst;
    }


    public boolean update(String id,String name,String phone, String email,String password)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Id",id);
        values.put("Name",name);
        values.put("Phone",phone);
        values.put("Email",email);
        values.put("Password",password);

        int res=db.update("Student",values,"Id=?",new String[]{id});
        return  res==1;
    }



}
