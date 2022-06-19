package com.example.calendar.Entity;

public class Course {
    private int id;
    private String courseInfo;

    public Course(String courseInfo) {
        this.courseInfo = courseInfo;
    }
    public Course(int id, String courseInfo) {
        this.id = id;
        this.courseInfo = courseInfo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCourseInfo() {
        return courseInfo;
    }
    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }
}
