package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseService extends BaseService {
    private final ArrayList<Course> allCourses;

    public CourseService() {
        this.allCourses = new ArrayList<>();
    }

    public Course getCourse(int id) throws EntityNotFoundException {

        for (Course course : allCourses) {
            if(course.getId() == id) {
                return course;
            }
        }

        throw new EntityNotFoundException("Course not found with id : " + id);
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) throws InvalidInputException {
        if(!InputValidator.isValidString(courseName)) {
            throw new InvalidInputException("Invalid Course Name entered");
        }

        if(!InputValidator.isValidString(description)) {
            description = "NA";
        }

        var id = IdGenerator.getNextCourseId();
        var course = new Course(id, courseName, description, durationInWeeks, true);

        allCourses.add(course);
        return course;
    }

    public Course[] getAllCourses() {
        if(allCourses.isEmpty()) {
            print("No Courses available");
            return null;
        }

        return allCourses.toArray(Course[]::new);
    }

    public void updateCourse(int id, boolean status) throws EntityNotFoundException {
        if(allCourses.isEmpty()) {
            print("No Courses available");
            return;
        }

        var course = getCourse(id);
        course.setActive(status);
    }

    public void listAllCourses() {
        if (allCourses.isEmpty()) {
            print("No Courses available");
            return;
        }

        println("=== AVAILABLE Courses ===");
        for (Course course : allCourses) {
            println(course.toString());
        }
    }
}
