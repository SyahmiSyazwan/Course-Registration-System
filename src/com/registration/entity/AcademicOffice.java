package com.registration.entity;

import java.util.ArrayList;
import java.util.List;

public class AcademicOffice extends User {
    public AcademicOffice(String id, String name) {
        super(id, name);
    }

    public void createCourse(String courseCode, String courseName, int minStudents, int maxStudents, List<Course> courses) {
        courses.add(new Course(courseCode, courseName, minStudents, maxStudents));
    }

    public void closeCourse(Course course) {
        course.closeCourse();
    }

    public void setCourseLimits(Course course, int minStudents, int maxStudents) {
        course.setMinStudents(minStudents);
        course.setMaxStudents(maxStudents);
    }

    public void displayCreatedCourses(List<Course> courses) {
        for (Course course : courses) {
            System.out.println(course.getCourseCode() + ": " + course.getCourseName());
        }
    }
}
