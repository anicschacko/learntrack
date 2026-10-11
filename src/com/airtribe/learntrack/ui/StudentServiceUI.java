package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

public class StudentServiceUI extends Main {

    private StudentService studentService;


    public StudentServiceUI(StudentService studentService) {
        this.studentService = studentService;
    }

    private boolean isInitialized() {
        return studentService != null;
    }

    public void ShowMainMenu() {
        int input = -1;

        println("-----------------------------");
        println("STUDENTS MANAGEMENT PORTAL");
        println("1. Add a Student");
        println("2. View all Students");
        println("3. Search Student by Id");
        println("4. Deactivate a Student");
        println("9. Move back to MainMenu");
        print("Please select an option: ");

        input = getIntegerInput(1, 2, 3, 4, 9);
        if(input == 9) {
            return;
        }

        ProcessMenuChoice(input);
        ShowMainMenu();
    }

    private void ProcessMenuChoice(int choice)
    {
        try {
            switch (choice){
                case 1: ShowAddStudentUI(); break;
                case 2: ViewAllStudentsUI(); break;
                case 3: ShowSearchStudentByIdUI(); break;
                case 4: ShowDeactivateStudentUI();
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    private void ShowAddStudentUI() throws Exception {
        if(!isInitialized()) {
            throw new Exception("Student Service not initialized");
        }

        println("-----------------------------");
        print("Enter Student First Name: ");
        var firstName = getInput(InputValidator::isValidName);
        print("Enter Student Last Name: ");
        var lastName = getInput(InputValidator::isValidName);
        print("Enter Student Email, Press enter to leave empty: ");
        var email = getInputOptional(InputValidator::isValidEmail);

        var student = studentService.addStudent(firstName, lastName, email);

        println("Added Student");
        printTable(Student.getHeaders(), new String[][]{
                student.getDetails()
        });
    }

    private void ViewAllStudentsUI() {
//        printTable(Student.getHeaders(), studentService.getAllStudentDetails());
        studentService.listAllStudents();
    }

    private void ShowSearchStudentByIdUI() {
        println("-----------------------------");
        println("Enter -1 to go back");
        print("Enter Student Id: ");
        var sId = getIntegerInput();
        if(sId == -1) {
            return;
        }

        try {
            var student = studentService.getStudent(sId);
            printTable(Student.getHeaders(), new String[][]{
                    student.getDetails()
            });

        } catch (EntityNotFoundException e) {
            println(e.getMessage());
        }
    }

    private void ShowDeactivateStudentUI() {
        println("-----------------------------");
        println("Enter -1 to go back");
        print("Enter Student Id: ");

        var sId = getIntegerInput();
        if(sId == -1) {
            return;
        }

        print("Confirm deactivate? (y/N): ");
        var confirmChar = getInput(s -> InputValidator.isStringValidChoice(s, "y", "n"));
        if(confirmChar.equalsIgnoreCase("n")) {
            return;
        }

        try {
            var student = studentService.getStudent(sId);
            studentService.updateStudent(sId, false);
            printTable(Student.getHeaders(), new String[][]{
                    student.getDetails()
            });

        } catch (EntityNotFoundException e) {
            println(e.getMessage());
        }
    }

}
