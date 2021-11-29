package com.example.studyplanner_200050013_200050130;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseClass extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "tasksDB";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "tasks";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our task name column
    private static final String NAME_COL = "name_of_task";

    // below variable id for our task date column.
    private static final String DATE_COL = "date_of_task";

    // below variable id for our task time column.
    private static final String TIME_COL = "time_of_task";

    // below variable for our course description column.
    private static final String DESCRIPTION_COL = "description";

    // creating a constructor for our database handler.
    public DatabaseClass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewTask(String courseName, String courseDate, String courseDescription, String courseTime) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DATE_COL, courseDate);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TIME_COL, courseTime);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // we have created a new method for reading all the courses.
    public ArrayList<TasksModel> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorTasks = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<TasksModel> tasksModelArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorTasks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                tasksModelArrayList.add(new TasksModel(cursorTasks.getString(1),
                        cursorTasks.getString(4),
                        cursorTasks.getString(2),
                        cursorTasks.getString(3)));
            } while (cursorTasks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorTasks.close();
        return tasksModelArrayList;
    }

}
