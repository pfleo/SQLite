package com.pf.www.sqlite.bean;

import java.io.Serializable;

/**
 * Created by Leo on 2016/12/29.
 */

public class Student implements Serializable {


    private int id;
    private String name;
    private String grade;

    public Student() {
    }

    public Student(int id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}