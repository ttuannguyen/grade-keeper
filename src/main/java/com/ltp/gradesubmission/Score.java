package com.ltp.gradesubmission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)
public @interface Score {
    
    // By convention, when we're creating a constraint annotation, we must always define the groups and the payload parameters 
    // We're overriding the below default error message in the Grade class (.i.e. "Score must be a letter grade")
    // Consider these boiler template code we need to include 
    
    String message() default "Invalid Data";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
