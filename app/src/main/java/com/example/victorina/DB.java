package com.example.victorina;

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

    public static ArrayList<Vopros> dbToClass(Context context) {

        int count = 0; // количество вопросов в базе

        //узнаем сколько вопросов в базе, сделая запрос COUNT
        String sql = "SELECT COUNT(Questions.text) FROM Questions";
        Cursor cursor = getDataFromBD(sql, context);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            count = Integer.parseInt(cursor.getString(0));
            Log.d("vic777", "количество вопросов в базе: " + count);
            cursor.moveToNext();
        }
        cursor.close();


        //выгрузим данные из бд в список вопросов
        List<Vopros> voprosList = new ArrayList<>();

        sql = "SELECT Questions.text, Questions.right_answer FROM Questions";
        Log.d("vic777", "всё");
        cursor = getDataFromBD(sql, context);
        cursor.moveToFirst();

        //цикл для только вопросов
        while (!cursor.isAfterLast()) {
               voprosList.add(new Vopros(cursor.getString(0), Integer.parseInt(cursor.getString(1))));
               Log.d("vic777", cursor.getString(0) + " " + cursor.getString(1));
               cursor.moveToNext();
        }


        //цикл для добавления ответов созданный ранее список voprosList
        sql = "SELECT Answers.id, Answers.text FROM Questions INNER JOIN Answers ON Questions.id = Answers.id";
        Log.d("vic777", "всё");
        cursor = getDataFromBD(sql, context);
        cursor.moveToFirst();

        List<String> answerList = new ArrayList<>(); // список ответов к каждому вопросу
        for (int i = 0; i < count; i++) {
            while (!cursor.isAfterLast()) {
                Log.d("vic777", "i= " + i);
                if (Integer.parseInt(cursor.getString(0)) == (i+1)){
                    answerList.add(cursor.getString(1));
                }
                voprosList.get(i).setAnswers(answerList);
                cursor.moveToNext();
            }
            answerList.clear();
            cursor.moveToFirst();
        }

        Log.d("vic777", voprosList.toString());

        return (ArrayList<Vopros>) voprosList;
    }
}
