package com.registration.entity;

import java.util.ArrayList;
import java.util.List;

public class AcademicOffice extends User {
    public AcademicOffice(String id, String name) {
        super(id, name);
    }

    public void createCourse(String courseCode, String courseName, int minStudents, int maxStudents,
            List<Course> courses) {
        courses.add(new Course(courseCode, courseName, minStudents, maxStudents));
    }

    public void closeCourse(Course course) {
        course.closeCourse();
    }

    public void openCourse(Course course) {
        course.openCourse();
    }

    public void setCourseLimits(Course course, int minStudents, int maxStudents) {
        course.setMinStudents(minStudents);
        course.setMaxStudents(maxStudents);
    }

    public void displayCreatedCourses(List<Course> courses) {
        if (courses.size() == 0) {
            System.out.println("No course has been created\n\n");
        } else {
            System.out.println("Created Courses:");
            for (Course course : courses) {
                String status = course.isOpen() ? "Open" : "Closed";
                System.out.printf("Course Code: %s, Course Name: %s, Status: %s\n", course.getCourseCode(),
                        course.getCourseName(), status);
            }
        }
    }
}
