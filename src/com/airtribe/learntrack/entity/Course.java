package com.airtribe.learntrack.entity;

public class Course {
    private final int id;
    private final String courseName;
    private final String description;
    private final int durationInWeeks;
    private boolean active;

    public Course(int id, String courseName, String description, int durationInWeeks, boolean active) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

    public static String[] getHeaders() {
        return new String[]{"Id", "Course Name", "Description", "Duration In Weeks", "Active"};
    }

    public int getId() { return id; }
    public String getCourseName() { return courseName; }
    public String getDescription() { return description; }
    public int getDurationInWeeks() { return durationInWeeks; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String[] getDetails() {
        return new String[]{
                String.valueOf(this.id),
                this.courseName,
                this.description,
                String.valueOf(this.durationInWeeks),
                String.valueOf(this.active)
        };
    }

    @Override
    public String toString() {
        return  getId() + ","
                + getCourseName() + ","
                + getDescription() + ","
                + getDurationInWeeks() + ","
                + isActive();
    }
}
