package com.ltp.gradesubmission.controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class GradeController {


    GradeRepository gradeRepository = new GradeRepository();
    
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        // @RequestParam (required) = false making the name param optional as it is only needed for updates
        // System.out.println(name);

        // Grade grade;
        
        // Method 1:
        // if (getGradeIndex(name) == -1) {
        //     // if not found, set the grade var = an empty grade object, which will eventually reset our form
        //     grade = new Grade();
        // } else {
        //     // if found, set the grade object based on the index found
        //     grade = studentGrades.get(getGradeIndex(name));
        // }
        // model.addAttribute("grade", grade);

        // Method 2: Refactored
        int index = getGradeIndex(id);
        model.addAttribute("grade", index == Constants.NOT_FOUND ? new Grade() : gradeRepository.getGrade(index));

        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeRepository.getGrades());
        return "grades";
    }

    // This handler method intercepts the POST request
    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        // Spring boot is going to create a grade object using the empty constructor in Grade class
        // Then, it's going to use the setters to update every single field inside of this grade object with payload from the POST request
        
        System.out.println("Has errors: " + result.hasErrors());
        if (result.hasErrors()) return "form";

        // System.out.println(grade.getSubject()); // For testing purposes after addeding the toString method
        // System.out.println(grade.getName());

        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            gradeRepository.addGrade(grade);
        } else {
            // if the grade already exists, we want to update it
            gradeRepository.updateGrade(grade, index);

        }

        // Redirect to all grades after submission bc submitSubmit doesn't handle rendering of new data
        return "redirect:/grades";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < gradeRepository.getGrades().size(); i++) {
            if (gradeRepository.getGrades().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND; // anything, representing index not found 
    }
    
}


