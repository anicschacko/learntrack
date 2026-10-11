package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.InputValidator;

public class EnrollmentServiceUI extends Main {

    private EnrollmentService enrollmentService;

    public EnrollmentServiceUI(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void ShowMainMenu() {
        int input = -1;

        println("-----------------------------");
        println("ENROLLMENT MANAGEMENT PORTAL");
        println("1. Enroll a student in a course");
        println("2. View all Enrollments");
        println("3. Update an enroll status");
        println("9. Move back to MainMenu");
        print("Please select an option: ");

        input = getIntegerInput(1, 2, 3, 9);
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
                case 1: ShowAddEnrollmentUI(); break;
                case 2: ShowAllEnrollments(); break;
                case 3: ShowUpdateEnrollmentUI(); break;
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    private void ShowAddEnrollmentUI() {
        println("-----------------------------");
        print("Enter Course Id: ");
        var cId = getIntegerInput();
        print("Enter Student Id: ");
        var sId = getIntegerInput();
        print("Enter Enrollment Date: ");
        var date = getInput(InputValidator::isValidString);
        var enrollment = enrollmentService.addEnrollment(cId, sId, date);
        println("Enrollment Added");
        println(enrollment.toString());
    }

    private void ShowAllEnrollments() {
        enrollmentService.listAllEnrollments();
    }

    private void ShowUpdateEnrollmentUI() throws EntityNotFoundException {
        println("-----------------------------");
        print("Enter Student Id: ");
        var sId = getIntegerInput();
        println("Select new Status: ");
        println("1. ACTIVE");
        println("2. COMPLETED");
        println("3. CANCELLED");

        print("Please select an option: ");
        var sChoice = getIntegerInput(1, 2, 3);

        enrollmentService.updateEnrollment(sId, EnrollmentStatus.values()[sChoice-1]);
    }
}
