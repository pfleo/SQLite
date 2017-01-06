package com.pf.www.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.pf.www.sqlite.bean.Student;
import com.pf.www.sqlite.db.DatabaseHandler;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etName,etGrade;
    private ImageView imageView;
    private Button btnChage,btnDelete,btnAdd;
    private int id;
    private DatabaseHandler handler;
    private Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);

        etName= (EditText) findViewById(R.id.student_name);
        etGrade= (EditText) findViewById(R.id.student_grade);
        btnChage= (Button) findViewById(R.id.btn_change);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        btnAdd= (Button) findViewById(R.id.btn_add_student);
        imageView= (ImageView) findViewById(R.id.student_image);


        handler=new DatabaseHandler(this);
        intent=getIntent();


        String request=intent.getStringExtra("request");
        switch (request){
            case "Add":
                btnChage.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
                break;

            case "Look":
                id=intent.getExtras().getInt("id");
                etName.setText(intent.getStringExtra("name"));
                etGrade.setText(intent.getStringExtra("grade"));
                if (id%2==0){
                    imageView.setImageResource(R.mipmap.girl1);
                }else{
                    imageView.setImageResource(R.mipmap.boy2);
                }
                break;
        }
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnChage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add_student:
                Student newStudent =new Student(id,etName.getText().toString(),etGrade.getText().toString());
                handler.addStudent(newStudent);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.btn_change:
                Student student=new Student(id,etName.getText().toString(),etGrade.getText().toString());
                handler.updateStudent(student);
                setResult(2,intent);
                finish();
                break;
            case R.id.btn_delete:
                Student s=new Student(id,etName.getText().toString(),etGrade.getText().toString());
                handler.deleteStudent(s);
                setResult(3,intent);
                finish();
                break;
        }
    }
}
