package ru.maryonegames.android.aliasgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by vamar on 07.12.2017.
 */

 public class words{


    static DatabaseHelper databaseHelper;
    static SQLiteDatabase db;
    static Cursor userCursor;


      static private ArrayList<one_word> game_words;
      static private ArrayList<String> words;
      static private ArrayList<String>  words_middle, words_hard;
      static private  ArrayList<explain_word> array_explain  = new ArrayList<explain_word>();
      static private int count_a, count_b;

    static private ArrayList<String> words_simple;
    static private ArrayList<String> words_simple2;

    private static void add_word (String lang, String add, String name) {
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ name, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        while (userCursor.moveToNext())
        {
            if (lang.equals("ru")) {
                words_simple.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_RU)));
                if (add.equals("en"))
                    words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_EN)));
                else if (add.equals("uk"))
                    words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_UK)));
            }
            else if (lang.equals("uk")){
                words_simple.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_UK)));
                if (add.equals("ru"))
                    words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_RU)));
                else if (add.equals("en"))
                    words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_EN)));
            }
             else {
            words_simple.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_EN)));
            if (add.equals("ru"))
                words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_RU)));
            else if (add.equals("en"))
                words_simple2.add(userCursor.getString(userCursor.getColumnIndex(DatabaseHelper.COLUMN_EN)));
        }
        }
        databaseHelper.close();

    }


     //@RequiresApi(api = Build.VERSION_CODES.N)
     public static void simple_game(Context context, String lang, String add, int base,
                                    int newyear, int starwars, int film, int famous, int middle, int valen) {

         words_simple = new ArrayList<String>();
         words_simple2 = new ArrayList<String>();

         count_a = 0; count_b = 0;

         databaseHelper = new DatabaseHelper(context);
         databaseHelper.create_db();

         if (base == 1) {
            add_word(lang, add, DatabaseHelper.TABLE_BASE);
         }

         if (newyear ==1) {
             add_word(lang, add, DatabaseHelper.TABLE_NEW_YEAR);
         }

         if (starwars ==1) {
             add_word(lang, add, DatabaseHelper.TABLE_STARWAR);
         }

         if (film ==1){
             add_word(lang, add, DatabaseHelper.TABLE_FILM);
         }

         if (famous ==1){
             add_word(lang, add, DatabaseHelper.TABLE_FAMOUS);
         }

         if (middle ==1) {
             add_word(lang, add, DatabaseHelper.TABLE_MIDDLE);
         }


         if (valen ==1){
             add_word(lang, add, DatabaseHelper.TABLE_VALEN);
         }

         game_words = new ArrayList<one_word>();
         for (int i = 0; i < game_words.size(); i++) game_words.remove(0);


         if (add.equals("none"))
         {
             for (int i = 0; i < words_simple.size(); i++) {
                 game_words.add(new one_word(words_simple.get(i)));
             }

         }
          else
             {
             for (int i = 0; i < words_simple.size(); i++) {
                 game_words.add(new one_word(words_simple.get(i), words_simple2.get(i)));
             }
        }



         Collections.sort(game_words, new Comparator<one_word>() {
             @Override
             public int compare(one_word one_word, one_word t1) {
                if ((one_word.getWord_for_explain().toUpperCase().compareTo(t1.getWord_for_explain().toUpperCase())) != 0)
                 return (one_word.getWord_for_explain().toUpperCase().compareTo(t1.getWord_for_explain().toUpperCase()));
                return 1;
             }
         });


         /*game_words.sort(new Comparator<one_word>() {
             @Override
             public int compare(one_word one_word, one_word t1) {

                 return one_word.getWord_for_explain().compareToIgnoreCase(t1.getWord_for_explain());

             }


         });*/

         int c=0;

         for (int i = 1; i < game_words.size(); i++){
             if (game_words.get(i-1).getWord_for_explain().equals(game_words.get(i).getWord_for_explain())){
                 game_words.remove(i);
                 i=i-1;
             }
         }

         int b = 0;

    }




    //@RequiresApi(api = Build.VERSION_CODES.N)
    public one_word getWord() {
        one_word word;
        int rand = 0;
        int min_freq;
        int len_freq;



        Collections.sort(game_words, new Comparator<one_word>() {
            @Override
            public int compare(one_word one_word, one_word t1) {
              return one_word.getFrequency() - t1.getFrequency();
              /*  if (one_word.getFrequency() == t1.getFrequency()) return 0;
                else if (one_word.getFrequency() > t1.getFrequency())
                    return 1;
                else
                    return -1;*/
            }
        });
        /*game_words.sort(new Comparator<one_word>() {
            @Override
            public int compare(one_word one_word, one_word t1) {
                if (one_word.getFrequency() == t1.getFrequency()) return 0;
                else if (one_word.getFrequency() > t1.getFrequency())
                    return 1;
                else
                    return -1;
            }


        });*/


        min_freq = game_words.get(0).getFrequency();

        for (len_freq = 0; len_freq < game_words.size(); len_freq++)
        {
            if (game_words.get(len_freq).getFrequency() != min_freq) break;
        }


        Random randNumber = new Random();
        rand = randNumber.nextInt(len_freq);

        word = game_words.get(rand);
        game_words.get(rand).setFrequency(game_words.get(rand).getFrequency()+1);
        return word;
    }

    public static void new_explain_word(String word, int res){

         array_explain.add(new explain_word(word,res));
    }

    public static void change_explain_word(int i, int res){
        explain_word word = array_explain.get(i);
        array_explain.get(i).setRes(res);

    }

    public static void delete_explain_word(int i){

        array_explain.remove(i);
    }

    public static ArrayList<explain_word> getArray_explain() {
        return array_explain;
    }

    public static void count_a_plus (int count){
        count_a = count_a+count;
    }

    public static void count_b_plus (int count){
        count_b = count_b+count;
    }

    public static int getCount_a() {
        return count_a;
    }

    public static int getCount_b() {
        return count_b;
    }
     public static void remove_explain(){
        array_explain.clear();
     }

     public static void delete_all_explain_words(){
         int size = array_explain.size();
         for (int i = 0; i < size; i++){
            array_explain.remove(0);
         }
     }
}
