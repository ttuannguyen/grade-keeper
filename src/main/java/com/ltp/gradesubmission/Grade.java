package com.ltp.gradesubmission;

import java.util.UUID;

public class Grade {
    private String name;
    private String subject;
    private String score;
    private String id;

    // Empty constructor
    public Grade() {
        // Added this new id
        this.id = UUID.randomUUID().toString();
    }
    // When updating, we're going to get 2 different IDs. So we need to add another input to the form, binding the grade's id field.
    // So even if the empty constructor generates a new ID for the grade, the data from the POST request is going to re-update all 4 fields.


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    // Generated using Java Code Generators for testing purposes (view inputs in terminal)
    // @Override
    // public String toString() {
    //     return "{" +
    //         " name='" + getName() + "'" +
    //         ", subject='" + getSubject() + "'" +
    //         ", score='" + getScore() + "'" +
    //         "}";
    // }

}
