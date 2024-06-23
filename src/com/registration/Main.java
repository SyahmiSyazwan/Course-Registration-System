package com.registration;

import com.registration.entity.AcademicOffice;
import com.registration.entity.Course;
import com.registration.entity.Lecturer;
import com.registration.entity.Student;
import com.registration.service.AcademicOfficeService;
import com.registration.service.LecturerService;
import com.registration.service.StudentService;
import com.registration.util.ConsoleUtil;
import com.registration.util.SerializationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String STUDENTS_FILE = "students.txt";
    private static final String LECTURERS_FILE = "lecturers.txt";
    private static final String COURSES_FILE = "courses.txt";

    public static void main(String[] args) throws Exception {
        List<Student> students = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        try {
            students = SerializationUtil.loadStudents(STUDENTS_FILE);
            lecturers = SerializationUtil.loadLecturers(LECTURERS_FILE);
            courses = SerializationUtil.loadCourses(COURSES_FILE, students, lecturers);
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }

        AcademicOffice academicOffice = new AcademicOffice("AO01", "Main Office");

        // Add 5 students if the list is empty
        if (students.isEmpty()) {
            students.add(new Student("S01", "Alice"));
            students.add(new Student("S02", "Bob"));
            students.add(new Student("S03", "Charlie"));
            students.add(new Student("S04", "Diana"));
            students.add(new Student("S05", "Edward"));
        }

        // Add 5 lecturers if the list is empty
        if (lecturers.isEmpty()) {
            lecturers.add(new Lecturer("L01", "Dr. Smith"));
            lecturers.add(new Lecturer("L02", "Dr. Johnson"));
            lecturers.add(new Lecturer("L03", "Dr. Brown"));
            lecturers.add(new Lecturer("L04", "Dr. Jones"));
            lecturers.add(new Lecturer("L05", "Dr. Miller"));
        }

        // Create service instances
        StudentService studentService = new StudentService(students, courses);
        LecturerService lecturerService = new LecturerService(lecturers, courses);
        AcademicOfficeService academicOfficeService = new AcademicOfficeService(academicOffice, courses);

        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //ConsoleUtil.clearScreen(); // Clear the console
            System.out.println("Welcome to the Course Registration System");
            System.out.println("1. Student");
            System.out.println("2. Academic Office");
            System.out.println("3. Lecturer");
            System.out.println("4. Exit");
            System.out.print("Choose your role: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    ConsoleUtil.clearScreen(); // Clear the console
                    studentService.showStudentMenu();
                    break;
                case 2:
                    ConsoleUtil.clearScreen(); // Clear the console
                    academicOfficeService.showAcademicOfficeMenu();
                    break;
                case 3:
                    ConsoleUtil.clearScreen(); // Clear the console
                    lecturerService.showLecturerMenu();
                    break;
                case 4:
                    try {
                        SerializationUtil.saveStudents(students, STUDENTS_FILE);
                        SerializationUtil.saveLecturers(lecturers, LECTURERS_FILE);
                        SerializationUtil.saveCourses(courses, COURSES_FILE);
                        System.out.println("Data saved. Exiting...");
                    } catch (IOException e) {
                        System.out.println("Error saving data: " + e.getMessage());
                    }
                    return;
                default:
                    ConsoleUtil.clearScreen(); // Clear the console
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
