package com.registration.entity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Course implements Serializable {
    private String courseCode;
    private String courseName;
    private List<Student> students;
    private Lecturer lecturer;
    private int minStudents;
    private int maxStudents;
    private boolean isOpen;

    public Course(String courseCode, String courseName, int minStudents, int maxStudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
        this.isOpen = true;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getMinStudents() {
        return minStudents;
    }

    public void setMinStudents(int minStudents) {
        this.minStudents = minStudents;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void closeCourse() {
        this.isOpen = false;
    }

    public void addStudent(Student student) throws Exception {
        if (students.size() >= maxStudents) {
            throw new Exception("Course is full.");
        }
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
