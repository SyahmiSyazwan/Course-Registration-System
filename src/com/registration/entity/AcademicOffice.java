package com.registration.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void listStudentsInCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            // Sort courses by number of students (descending)
            Collections.sort(courses, new Comparator<Course>() {
                @Override
                public int compare(Course c1, Course c2) {
                    return Integer.compare(c2.getStudents().size(), c1.getStudents().size());
                }
            });

            for (Course course : courses) {
                System.out.printf("Course Code: %s, Course Name: %s\n", course.getCourseCode(), course.getCourseName());
                List<Student> students = course.getStudents();
                if (students.isEmpty()) {
                    System.out.println("No students enrolled in this course.");
                } else {
                    System.out.printf("%-20s%-20s\n", "Student ID", "Student Name");
                    for (Student student : students) {
                        System.out.printf("%-20s%-20s\n", student.getId(), student.getName());
                    }
                }
                System.out.println();
            }
        }
    }

    public void listLecturersTeachingCourses(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            // Sort courses by whether a lecturer is assigned (assigned first) and then by number of students (descending)
            Collections.sort(courses, new Comparator<Course>() {
                @Override
                public int compare(Course c1, Course c2) {
                    int lecturerComparison = Boolean.compare(c2.getLecturer() != null, c1.getLecturer() != null);
                    if (lecturerComparison != 0) {
                        return lecturerComparison;
                    }
                    return Integer.compare(c2.getStudents().size(), c1.getStudents().size());
                }
            });

            for (Course course : courses) {
                System.out.printf("Course Code: %s, Course Name: %s\n", course.getCourseCode(), course.getCourseName());
                Lecturer lecturer = course.getLecturer();
                if (lecturer == null) {
                    System.out.println("No lecturer assigned to this course.");
                } else {
                    System.out.printf("Lecturer ID: %s, Lecturer Name: %s\n", lecturer.getId(), lecturer.getName());
                }
                System.out.println();
            }
        }
    }

    public void displayCourseInformation(List<Course> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            // Sort courses by number of students (descending) and then by lecturer assignment
            Collections.sort(courses, new Comparator<Course>() {
                @Override
                public int compare(Course c1, Course c2) {
                    int studentCountComparison = Integer.compare(c2.getStudents().size(), c1.getStudents().size());
                    if (studentCountComparison != 0) {
                        return studentCountComparison;
                    }
                    return Boolean.compare(c2.getLecturer() != null, c1.getLecturer() != null);
                }
            });

            for (Course course : courses) {
                System.out.printf("Course Code: %s, Course Name: %s\n", course.getCourseCode(), course.getCourseName());
                Lecturer lecturer = course.getLecturer();
                if (lecturer != null) {
                    System.out.printf("Lecturer ID: %s, Lecturer Name: %s\n", lecturer.getId(), lecturer.getName());
                } else {
                    System.out.println("No lecturer assigned.");
                }

                List<Student> students = course.getStudents();
                if (students.isEmpty()) {
                    System.out.println("No students enrolled in this course.");
                } else {
                    System.out.printf("%-20s%-20s\n", "Student ID", "Student Name");
                    for (Student student : students) {
                        System.out.printf("%-20s%-20s\n", student.getId(), student.getName());
                    }
                }
                System.out.println();
            }
        }
    }
}
