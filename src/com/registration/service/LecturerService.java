package com.registration.service;

import com.registration.entity.Course;
import com.registration.entity.Lecturer;
import com.registration.util.ConsoleUtil;

import java.util.List;
import java.util.Scanner;

public class LecturerService {
    private List<Lecturer> lecturers;
    private List<Course> courses;

    public LecturerService(List<Lecturer> lecturers, List<Course> courses) {
        this.lecturers = lecturers;
        this.courses = courses;
    }

    public void showLecturerMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your lecturer ID: ");
        String lecturerId = scanner.nextLine();

        Lecturer lecturer = findLecturerById(lecturerId);
        if (lecturer == null) {
            System.out.println("Lecturer not found.\n\n");
            return;
        }

        while (true) {
            ConsoleUtil.clearScreen();
            System.out.println("Welcome back, " + lecturer.getName() + ": " + lecturer.getId());
            System.out.println("Lecturer Menu");
            System.out.println("1. Register to teach course");
            System.out.println("2. Drop taught course");
            System.out.println("3. View teaching courses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ConsoleUtil.clearScreen();
                    System.out.print("Enter course code to teach: ");
                    String courseCodeToTeach = scanner.nextLine();
                    Course courseToTeach = findCourseByCode(courseCodeToTeach);
                    if (courseToTeach != null) {
                        try {
                            lecturer.registerToTeachCourse(courseToTeach);
                            System.out.println("Course registered successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 2:
                    ConsoleUtil.clearScreen();
                    lecturer.viewTeachingCourses();
                    System.out.print("Enter course code to drop: ");
                    String courseCodeToDrop = scanner.nextLine();
                    Course courseToDrop = findCourseByCode(courseCodeToDrop);
                    if (courseToDrop != null) {
                        lecturer.dropTaughtCourse(courseToDrop);
                        System.out.println("Course dropped successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    ConsoleUtil.clearScreen();
                    lecturer.viewTeachingCourses();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private Lecturer findLecturerById(String id) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getId().equals(id)) {
                return lecturer;
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
