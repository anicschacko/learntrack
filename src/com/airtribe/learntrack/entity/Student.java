package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
        this.active = true;
    }

    // Constructor Overloading for Student's without emailId
    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName, "NA");
        this.active = true;
    }

    public String getBatch() {
        return batch;
    }
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Student)";
    }

    @Override
    public String toString() {
        return super.toString() + "," +
                getBatch() + "," +
                isActive();
    }

    public static String[] getHeaders() {
        return new String[]{"ID", "Name", "Email", "Batch", "Active"};
    }

    public String[] getDetails() {
        return new String[]{
                String.valueOf(this.getId()),
                this.getDisplayName(),
                this.getEmail(),
                String.valueOf(this.getBatch()),
                String.valueOf(this.isActive())
        };
    }
}
