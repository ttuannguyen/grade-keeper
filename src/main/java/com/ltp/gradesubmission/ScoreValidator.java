package com.ltp.gradesubmission;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScoreValidator implements ConstraintValidator<Score, String> {
    
    // List created to compare the input for validation in for loop below
    List<String> scores = Arrays.asList(
        "A+", "A" , "A-",
        "B+", "B" , "B-",
        "C+", "C" , "C-",
        "D+", "D" , "D-",
        "F"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // This method is the heart of where the validation is happening, returning T if validated, otherwise returning F
        for (String string : scores) {
            if (value.equals(string)) return true;
        }
        return false;
    }
}
