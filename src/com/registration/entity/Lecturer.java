package com.registration.entity;

import java.util.ArrayList;
import java.util.List;

public class Lecturer extends User {
    private List<Course> teachingCourses;

    public Lecturer(String id, String name) {
        super(id, name);
        this.teachingCourses = new ArrayList<>();
    }

    public void registerToTeachCourse(Course course) throws Exception {
        if (teachingCourses.contains(course)) {
            throw new Exception("You are already registered to teach this course.");
        }
        if (course.getLecturer() != null) {
            throw new Exception("Course is already assigned to another lecturer.");
        }
        course.setLecturer(this);
        teachingCourses.add(course);
    }

    public void dropTaughtCourse(Course course) {
        course.setLecturer(null);
        teachingCourses.remove(course);
    }

    public void viewTeachingCourses() {
        if (teachingCourses.isEmpty()) {
            System.out.println("No teaching courses.");
            return;
        }
        for (Course course : teachingCourses) {
            System.out.println(course.getCourseCode() + ": " + course.getCourseName());
        }
    }
    public void viewAvailableCourses(List<Course> courses) {
        for (Course course : courses) {
            if (course.isOpen() && !teachingCourses.contains(course) && course.getLecturer() == null) {
                System.out.println(course.getCourseCode() + ": " + course.getCourseName());
            }
        }
    }

    public Boolean checkTeachingCourseNotNull() {
        return teachingCourses.isEmpty();
    }
}
