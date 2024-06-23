package com.registration.entity;

import java.util.ArrayList;
import java.util.List;
import com.registration.util.ConsoleUtil;

public class Student extends User {
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        super(id, name);
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) throws Exception {
        if (!course.isOpen()) {
            throw new Exception("Course is closed.");
        }
        course.addStudent(this);
        registeredCourses.add(course);
    }

    public void dropCourse(Course course) {
        course.removeStudent(this);
        registeredCourses.remove(course);
    }

    public void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses.");
            return;
        }
        for (Course course : registeredCourses) {
            System.out.println(course.getCourseCode() + ": " + course.getCourseName());
        }
    }

    
    public void viewAvailableCourses(List<Course> courses) {
        ConsoleUtil.clearScreen(); // Clear the console
        System.out.println("Available Courses:");
        for (Course course : courses) {
            String status = course.isOpen() ? "Open" : "Closed";
            System.out.printf("Course Code: %s, Course Name: %s, Status: %s\n", course.getCourseCode(), course.getCourseName(), status);
        }
        System.out.println("Press Enter to continue...");
    }
}
