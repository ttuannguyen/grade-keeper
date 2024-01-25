package com.ltp.gradesubmission.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.service.GradeService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {
    
    @Autowired
    GradeService gradeService;
    // GradeService gradeService = new GradeService(); // room for tight coupling, making it impossible for unit test
    
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


