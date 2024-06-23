package com.registration.service;

import com.registration.entity.AcademicOffice;
import com.registration.entity.Course;
import com.registration.util.ConsoleUtil;

import java.util.List;
import java.util.Scanner;

public class AcademicOfficeService {
    private AcademicOffice academicOffice;
    private List<Course> courses;

    public AcademicOfficeService(AcademicOffice academicOffice, List<Course> courses) {
        this.academicOffice = academicOffice;
        this.courses = courses;
    }

    public void showAcademicOfficeMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // ConsoleUtil.clearScreen();
            System.out.println("Academic Office Menu");
            System.out.println("1. Create course");
            System.out.println("2. Close course");
            System.out.println("3. Open course");
            System.out.println("4. Display created courses");
            System.out.println("5. Set course limits");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ConsoleUtil.clearScreen();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter minimum number of students: ");
                    int minStudents = scanner.nextInt();
                    System.out.print("Enter maximum number of students: ");
                    int maxStudents = scanner.nextInt();
                    academicOffice.createCourse(courseCode, courseName, minStudents, maxStudents, courses);
                    System.out.println("Course created successfully.");
                    break;
                case 2:
                    ConsoleUtil.clearScreen();
                    if (courses.size() == 0) {
                        System.out.println("No course has been created\n\n");
                        break;
                    }
                    System.out.print("Enter course code to close: ");
                    String courseCodeToClose = scanner.nextLine();
                    Course courseToClose = findCourseByCode(courseCodeToClose);
                    if (courseToClose != null) {
                        academicOffice.closeCourse(courseToClose);
                        System.out.println("Course closed successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    ConsoleUtil.clearScreen();
                    if (courses.size() == 0) {
                        System.out.println("No course has been created\n\n");
                        break;
                    }
                    System.out.print("Enter course code to close: ");
                    String courseCodeToOpen = scanner.nextLine();
                    Course courseToOpen = findCourseByCode(courseCodeToOpen);
                    if (courseToOpen != null) {
                        academicOffice.closeCourse(courseToOpen);
                        System.out.println("Course open successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    ConsoleUtil.clearScreen();
                    academicOffice.displayCreatedCourses(courses);
                    break;
                case 5:
                    ConsoleUtil.clearScreen();
                    academicOffice.displayCreatedCourses(courses);
                    System.out.print("Enter course code to set limits: ");
                    String courseCodeToSetLimits = scanner.nextLine();
                    Course courseToSetLimits = findCourseByCode(courseCodeToSetLimits);
                    if (courseToSetLimits != null) {
                        System.out.print("Enter new minimum number of students: ");
                        int newMinStudents = scanner.nextInt();
                        System.out.print("Enter new maximum number of students: ");
                        int newMaxStudents = scanner.nextInt();
                        academicOffice.setCourseLimits(courseToSetLimits, newMinStudents, newMaxStudents);
                        System.out.println("Course limits set successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 6:
                    ConsoleUtil.clearScreen();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
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
