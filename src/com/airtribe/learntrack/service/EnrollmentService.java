package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class EnrollmentService extends BaseService {
    private ArrayList<Enrollment> allEnrollments;
    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService() {
        this.allEnrollments = new ArrayList<>();
    }

    public Enrollment addEnrollment(int courseId, int studentId, String enrollmentData) {
        var id = IdGenerator.getNextEnrollmentId();
        var enrollment = new Enrollment(id, studentId, courseId, enrollmentData, EnrollmentStatus.ACTIVE);
        allEnrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment getEnrollment(int studentId) throws EntityNotFoundException {
        for (var enrollment : allEnrollments) {
            if(enrollment.getStudentId() == studentId) {
                return enrollment;
            }
        }

        throw new EntityNotFoundException("Student id: " + studentId + " not yet enrolled");
    }

    public void listAllEnrollments() {
        if(allEnrollments.isEmpty()) {
            print("No enrollments Available");
            return;
        }

        for (var enrollment : allEnrollments) {
            System.out.println(enrollment);
        }
    }

    public void updateEnrollment(int studentId, EnrollmentStatus status) throws EntityNotFoundException {
        if(allEnrollments.isEmpty()) {
            print("No enrollments Available");
            return;
        }

        var enrollment = getEnrollment(studentId);
        enrollment.setStatus(status);
    }
}
