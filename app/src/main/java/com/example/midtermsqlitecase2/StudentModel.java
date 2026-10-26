package com.example.midtermsqlitecase2;

public class StudentModel {

    Integer id;
    String studentName;
    String studentID;
    String enrolled;

    public StudentModel(
            String studentName,
            String studentID,
            String enrolled) {

        this.studentName = studentName;
        this.studentID = studentID;
        this.enrolled = enrolled;
    }

    public StudentModel(
            Integer id,
            String studentName,
            String studentID,
            String enrolled) {

        this.id = id;
        this.studentName = studentName;
        this.studentID = studentID;
        this.enrolled = enrolled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(String enrolled) {
        this.enrolled = enrolled;
    }
}