package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.util.InputValidator;

public class CourseServiceUI extends Main{

    private CourseService courseService;

    public CourseServiceUI(CourseService courseService) {
        this.courseService = courseService;
    }

    public void ShowMainMenu() {
        int input = -1;

        println("-----------------------------");
        println("COURSE MANAGEMENT PORTAL");
        println("1. Add a Course");
        println("2. View all Courses");
        println("3. Activate/Deactivate a Course");
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
                case 1: ShowAddCourseUI(); break;
                case 2: ShowAllCoursesUI(); break;
                case 3: ShowCourseActivateUI(); break;
            }
        } catch (Exception e) {
            print(e.getMessage());
        }
    }

    private void ShowAddCourseUI() throws Exception {
        println("-----------------------------");
        print("Enter Course Name: ");
        var courseName = getInput(InputValidator::isValidName);
        print("Enter Course Description: ");
        var description = getInput(InputValidator::isValidString);
        print("Enter Course duration in weeks: ");
        var durationInWeeks = getIntegerInput();

        var course = courseService.addCourse(courseName, description, durationInWeeks);

        println("Added Course..");
        printTable(Course.getHeaders(), new String[][]{
                course.getDetails()
        });
    }

    private void ShowAllCoursesUI() {
        courseService.listAllCourses();
    }

    private void ShowCourseActivateUI() {
        println("-----------------------------");
        println("Enter -1 to go back");
        print("Enter Course Id: ");

        var cId = getIntegerInput();
        if(cId == -1) {
            return;
        }

        try {
            var course = courseService.getCourse(cId);
            println("Current Status of Course with id: " + cId);
            printTable(Student.getHeaders(), new String[][]{
                    course.getDetails()
            });

            print("New Status (T/F): ");
            var statusInput = getInput(s -> InputValidator.isStringValidChoice(s, "t", "f"));
            var status = statusInput.equalsIgnoreCase("t");

            if(status == course.isActive()) {
                println("Status is already " + status);
            }
            else {
                courseService.updateCourse(cId, statusInput.equalsIgnoreCase("t"));
                println("Updated Course Status..");
                printTable(Student.getHeaders(), new String[][]{
                        course.getDetails()
                });
            }
        } catch (EntityNotFoundException e) {
            println(e.getMessage());
        }
    }
}
