package com.registration.util;

import com.registration.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationUtil {

    // Generic method to save objects to file
    public static <T> void saveObjects(List<T> objects, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(objects);
        }
    }

    // Generic method to load objects from file
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadObjects(String fileName) throws IOException, ClassNotFoundException {
        List<T> objects;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            objects = (List<T>) ois.readObject();
        }
        return objects;
    }

    // Specific method to save students to file
    public static void saveStudents(List<Student> students, String fileName) throws IOException {
        saveObjects(students, fileName);
    }

    // Specific method to load students from file
    public static List<Student> loadStudents(String fileName) throws IOException, ClassNotFoundException {
        return loadObjects(fileName);
    }

    // Specific method to save lecturers to file
    public static void saveLecturers(List<Lecturer> lecturers, String fileName) throws IOException {
        saveObjects(lecturers, fileName);
    }

    // Specific method to load lecturers from file
    public static List<Lecturer> loadLecturers(String fileName) throws IOException, ClassNotFoundException {
        return loadObjects(fileName);
    }

    // Specific method to save courses to file
    public static void saveCourses(List<Course> courses, String fileName) throws IOException {
        saveObjects(courses, fileName);
    }

    // Specific method to load courses from file
    public static List<Course> loadCourses(String fileName) throws IOException, ClassNotFoundException {
        return loadObjects(fileName);
    }
}
