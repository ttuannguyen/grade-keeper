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
import com.ltp.gradesubmission.service.GradeService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class GradeController {


    GradeRepository gradeRepository = new GradeRepository();
    GradeService gradeService = new GradeService();
    
    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        // @RequestParam (required) = false making the name param optional as it is only needed for updates
        // System.out.println(name);

        model.addAttribute("grade", gradeService.getGradeById(id));

        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getGrades());
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

        gradeService.submitGrade(grade);

        // Redirect to all grades after submission bc submitSubmit doesn't handle rendering of new data
        return "redirect:/grades";
    }
    
}


