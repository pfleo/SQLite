package com.pf.www.sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pf.www.sqlite.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leo on 2016/12/28.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Test";
    private static final String TABLE_NAME="student";
    private static final int VERSION=1;
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_GRADE="grade";

    private static final String CREATE_TABLE="create table "+TABLE_NAME+"("+KEY_ID+
            " integer primary  key autoincrement,"+KEY_NAME+" varchar(20) not null,"+
            KEY_GRADE+" text not null);" ;



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqb) {
        sqb.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }


    public void addStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_GRADE,student.getGrade());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public Student getStudent(String name){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cv=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_GRADE},
                KEY_NAME+"=?",new String[]{name},null,null,null);

        Student student=null;
        if (cv.moveToFirst()){
            student=new Student(cv.getInt(0),cv.getString(1),cv.getString(2));
        }
        return student;
    }


    public int getStudentCounts(){
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cv=db.rawQuery(selectQuery,null);
        cv.close();

        return cv.getCount();
    }


    public List<Student> getAllStudent(){
        List<Student> studentList=new ArrayList<Student>();
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cv=db.rawQuery(selectQuery,null);
        if (cv.moveToFirst()){
            do {
                Student student=new Student();
                student.setId(cv.getInt(0));
                student.setName(cv.getString(1));
                student.setGrade(cv.getString(2));
                studentList.add(student);
            }while (cv.moveToNext());
        }
        return studentList;
    }

    public int updateStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_GRADE,student.getGrade());
        return db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(student.getId())});
        db.close();
    }


}
