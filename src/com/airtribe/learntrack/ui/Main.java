package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;
import java.util.function.Predicate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main  {
    private static Scanner scanner;
    private static StudentServiceUI studentServiceUI;
    private static CourseServiceUI courseServiceUI;
    private static EnrollmentServiceUI enrollmentServiceUI;


    public static void main(String[] args) {

        System.out.println("Hello, World!");
//        InitServiceClasses();
//
//        scanner = new Scanner(System.in);
//        int choice = -1;
//        do {
//            choice = ShowMainMenu();
//            try {
//                switch (choice) {
//                    case 1:
//                        studentServiceUI.ShowMainMenu();
//                        break;
//                    case 2:
//                        courseServiceUI.ShowMainMenu();
//                        break;
//                    case 3:
//                        enrollmentServiceUI.ShowMainMenu();
//                        break;
//                    case 0:
//                        println("Thank you for using LearnTrack Portal!!");
//                        break;
//
//                }
//            } catch (Exception e) {
//                println("Error : " + e.getMessage());
//            }
//        } while(choice != 0);
    }

    private static void InitServiceClasses() {
        studentServiceUI = new StudentServiceUI(new StudentService());
        courseServiceUI = new CourseServiceUI(new CourseService());
        enrollmentServiceUI = new EnrollmentServiceUI(new EnrollmentService());
    }

    private static int ShowMainMenu() {
        println("-----------------------------");
        println("WELCOME TO LEANTRACK PORTAL ");
        println("1. Student Management");
        println("2. Course Management");
        println("3. Enrollment Management");
        println("0. Exit");

        print("Please select an option: ");


        return getIntegerInput(0, 1, 2, 3);
    }

    public static String getInput(Predicate<String> validator) {
        try {
            var s = scanner.nextLine();
            if(!validator.test(s)) {
                throw new InvalidInputException("Invalid String Input");
            }

            return s;
        } catch (Exception e) {
            print("Invalid input. Please try again: ");
            return getInput(validator);
        }
    }

    public static String getInputOptional(Predicate<String> validator) {
        try {
            var s = scanner.nextLine();
            if(!InputValidator.isValidString(s)) {
                return s;
            }

            if(!validator.test(s)) {
                throw new InvalidInputException("Invalid String Input");
            }

            return s;
        } catch (Exception e) {
            print("Invalid input. Please try again: ");
            return getInput(validator);
        }
    }

    public static int getIntegerInput(int... validChoices) {
        int input;

        try {
            input = Integer.parseInt(scanner.nextLine());

            if(validChoices != null && validChoices.length > 0) {
                for (int validChoice : validChoices) {
                    if (input == validChoice) {
                        return input;
                    }
                }
            }
            else {
                return input;
            }

            throw new InvalidInputException("Invalid Choice Selected");

        } catch (Exception e) {
            println("Invalid input. Please enter a valid choice: ");
            return getIntegerInput(validChoices);
        }
    }

    protected static void println(String message) {
        System.out.println(message);
    }
    protected static void print(String message) {
        System.out.print(message);
    }

    public static void printTable(String[] headers, String[][] rows) {
        int[] widths = new int[headers.length];

        // Calculate column widths
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();

            for (String[] row : rows) {
                if (i < row.length && row[i] != null) {
                    widths[i] = Math.max(widths[i], row[i].length());
                }
            }
        }

        // Print header
        printRow(headers, widths);

        // Print content
        for (String[] row : rows) {
            printRow(row, widths);
        }
    }

    public static void printRow(String[] values, int[] widths) {
        for (int i = 0; i < widths.length; i++) {
            String value = i < values.length && values[i] != null
                    ? values[i] : "";

            System.out.printf("%-" + (widths[i] + 4) + "s", value);
        }
        System.out.println();
    }
}