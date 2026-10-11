package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.*;
import com.airtribe.learntrack.util.*;

import java.util.ArrayList;

public class StudentService extends BaseService {
    private final ArrayList<Student> allStudents;

    public StudentService() {
        this.allStudents = new ArrayList<>();
    }

    public Student getStudent(int id) throws EntityNotFoundException {

        for (Student s : allStudents) {
            if (s.getId() == id) {
                return s;
            }
        }

        throw new EntityNotFoundException("Student not found with id: " + id);
    }

    public Student addStudent(String firstName, String lastName, String email) throws InvalidInputException {

        if (!InputValidator.isValidString(firstName)) {
            throw new InvalidInputException("Invalid first name entered");
        }

        if (!InputValidator.isValidString(lastName)) {
            lastName = "-";
        }

        var id = IdGenerator.getNextStudentId();

        Student newStudent;
        if (!InputValidator.isValidEmail(email)) {
            print("Skipping Invalid Email..");
            newStudent = new Student(id, firstName, lastName);
        } else {
            newStudent = new Student(id, firstName, lastName, email);
        }

        allStudents.add(newStudent);
        return newStudent;
    }

    public Student removeStudent(int id) throws EntityNotFoundException {
        if (allStudents.isEmpty()) {
            print("No Students available");
            return null;
        }

        var student = getStudent(id);
        allStudents.remove(student);
        return student;
    }

    public boolean updateStudent(int id, boolean status) throws EntityNotFoundException {
        if (allStudents.isEmpty()) {
            print("No Students available");
            return false;
        }

        var student = getStudent(id);
        student.setActive(status);
        return true;
    }

    public void listAllStudents() {
        if (allStudents.isEmpty()) {
            print("No Students available");
            return;
        }

        println("=== AVAILABLE STUDENTS ===");
        for (Student student : allStudents) {
            println(student.toString());
        }
    }

}
