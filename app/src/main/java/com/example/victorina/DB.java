package com.example.victorina;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DB {

    //Select запрос к базе в виде объекта Cursor
    public static Cursor getDataFromBD(String sqlQuery, Context context){
        DatabaseHelper databaseHelper;
        SQLiteDatabase bd;
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        Cursor cursor;
        cursor = bd.rawQuery(sqlQuery,null);
        return cursor;
    }
 /*   //INSERT запрос для таблицы пользователь к базе данных
    public static void addUser(User user, Context context){
        DatabaseHelper databaseHelper;
        SQLiteDatabase bd;
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("login", user.getLogin());
        newValues.put("pswrd", user.getPswrd());
        newValues.put("fio", user.getFio());
        newValues.put("adress", user.getFio());
        bd.insert("users", null, newValues);
    }

    //INSERT запрос для таблицы пользователь к базе данных
    public static void addBook(Book book, Context context){
        DatabaseHelper databaseHelper;
        SQLiteDatabase bd;
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("title", book.getTitle());
        newValues.put("author", book.getAuthor());
        newValues.put("annotation", book.getAnnotation());
        newValues.put("page_count", book.getPageCount());
        newValues.put("year_of_publ", book.getYearOfPubl());
        newValues.put("wish", "no");
        bd.insert("books", null, newValues);
    }



    //UPDATE запрос для обновления данных в БД
    public static void updateBook(int id, String wish, Context context){
        DatabaseHelper databaseHelper;
        SQLiteDatabase bd;
        databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.updateDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bd = databaseHelper.getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("wish", wish);
        int updCount = bd.update("books",newValues,"id = ?", new String[]{String.valueOf(id)});

    }*/

    public static ArrayList<String> allQuestions(Context context) {

        List<String> list = new ArrayList<>();

        String sql = "SELECT Questions.text, Questions.right_answer, Answers.text FROM Questions INNER JOIN Answers ON Questions.id = Answers.id";
        Log.d("vic777", "всё");
        Cursor cursor = getDataFromBD(sql, context);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Log.d("vic777", cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
           // list.add(cursor.getString(1) + " руб. категория: " + cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return (ArrayList<String>) list;
    }
}
