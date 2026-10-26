package com.example.midtermsqlitecase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "student_case2_db";

    private static final String TB_NAME = "student";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "student_name";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_ENROLLED = "enrolled";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TB_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT NOT NULL, " +
                    COL_STUDENT_ID + " TEXT NOT NULL, " +
                    COL_ENROLLED + " TEXT NOT NULL);";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }

    public void addStudent(StudentModel studentModel) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(
                COL_NAME,
                studentModel.getStudentName()
        );

        contentValues.put(
                COL_STUDENT_ID,
                studentModel.getStudentID()
        );

        contentValues.put(
                COL_ENROLLED,
                studentModel.getEnrolled()
        );

        SQLiteDatabase db = getWritableDatabase();

        db.insert(
                TB_NAME,
                null,
                contentValues
        );
    }

    public void updateStudent(StudentModel studentModel) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(
                COL_NAME,
                studentModel.getStudentName()
        );

        contentValues.put(
                COL_STUDENT_ID,
                studentModel.getStudentID()
        );

        contentValues.put(
                COL_ENROLLED,
                studentModel.getEnrolled()
        );

        SQLiteDatabase db = getWritableDatabase();

        db.update(
                TB_NAME,
                contentValues,
                COL_ID + " = ?",
                new String[]{
                        String.valueOf(studentModel.getId())
                }
        );
    }

    public void deleteStudent(int id) {

        SQLiteDatabase db = getWritableDatabase();

        db.delete(
                TB_NAME,
                COL_ID + " = ?",
                new String[]{
                        String.valueOf(id)
                }
        );
    }

    public List<StudentModel> getStudentList() {

        List<StudentModel> studentModels =
                new ArrayList<>();

        SQLiteDatabase db =
                getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TB_NAME,
                null
        );

        if (cursor.moveToFirst()) {

            do {

                int id =
                        cursor.getInt(
                                cursor.getColumnIndexOrThrow(
                                        COL_ID
                                )
                        );

                String studentName =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        COL_NAME
                                )
                        );

                String studentID =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        COL_STUDENT_ID
                                )
                        );

                String enrolled =
                        cursor.getString(
                                cursor.getColumnIndexOrThrow(
                                        COL_ENROLLED
                                )
                        );

                studentModels.add(
                        new StudentModel(
                                id,
                                studentName,
                                studentID,
                                enrolled
                        )
                );

            } while (cursor.moveToNext());
        }

        cursor.close();

        return studentModels;
    }
}