package com.registration.util;

import com.registration.entity.*;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class SerializationUtil {

    public static void saveStudents(List<Student> students, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName());
                writer.newLine();
            }
        }
    }

    public static void saveLecturers(List<Lecturer> lecturers, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Lecturer lecturer : lecturers) {
                writer.write(lecturer.getId() + "," + lecturer.getName());
                writer.newLine();
            }
        }
    }

    public static void saveCourses(List<Course> courses, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Course course : courses) {
                writer.write(course.getCourseCode() + "," + course.getCourseName() + "," +
                        course.getMinStudents() + "," + course.getMaxStudents() + "," +
                        (course.getLecturer() != null ? course.getLecturer().getId() : ""));
                writer.newLine();
                for (Student student : course.getStudents()) {
                    writer.write("  " + student.getId());
                    writer.newLine();
                }
            }
        }
    }

    public static List<Student> loadStudents(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    students.add(new Student(parts[0], parts[1]));
                }
            }
        }
        return students;
    }

    public static List<Lecturer> loadLecturers(String fileName) throws IOException {
        List<Lecturer> lecturers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    lecturers.add(new Lecturer(parts[0], parts[1]));
                }
            }
        }
        return lecturers;
    }

    public static List<Course> loadCourses(String fileName, List<Student> students, List<Lecturer> lecturers) throws IOException {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Course currentCourse = null;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("  ")) {
                    String[] parts = line.split(",");
                    if (parts.length >= 4) {
                        currentCourse = new Course(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                        if (parts.length == 5 && !parts[4].isEmpty()) {
                            for (Lecturer lecturer : lecturers) {
                                if (lecturer.getId().equals(parts[4])) {
                                    currentCourse.setLecturer(lecturer);
                                    break;
                                }
                            }
                        }
                        courses.add(currentCourse);
                    }
                } else if (currentCourse != null) {
                    String studentId = line.trim();
                    for (Student student : students) {
                        if (student.getId().equals(studentId)) {
                            currentCourse.addStudent(student);
                            break;
                        }
                    }
                }
            }
        }
        return courses;
    }
}
