package com.registration.service;

import com.registration.entity.Course;
import com.registration.entity.Student;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student> students;
    private List<Course> courses;

    public StudentService(List<Student> students, List<Course> courses) {
        this.students = students;
        this.courses = courses;
    }

    public void showStudentMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();

        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        while (true) {
            System.out.println("Student Menu");
            System.out.println("1. Register course");
            System.out.println("2. View available courses");
            System.out.println("3. View registered courses");
            System.out.println("4. Drop course");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    student.viewAvailableCourses(courses);
                    System.out.print("Enter course code to register: ");
                    String courseCodeToRegister = scanner.nextLine();
                    Course courseToRegister = findCourseByCode(courseCodeToRegister);
                    if (courseToRegister != null) {
                        try {
                            student.registerCourse(courseToRegister);
                            System.out.println("Course registered successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 2:
                    student.viewAvailableCourses(courses);
                    break;
                case 3:
                    student.viewRegisteredCourses();
                    break;
                case 4:
                    student.viewRegisteredCourses();
                    System.out.print("Enter course code to drop: ");
                    String courseCodeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourseByCode(courseCodeToDrop);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}