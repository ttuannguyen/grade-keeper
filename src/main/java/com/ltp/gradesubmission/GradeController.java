package com.ltp.gradesubmission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller

public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

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
        model.addAttribute("grade", getGradeIndex(id) == -1 ? new Grade() : studentGrades.get(getGradeIndex(id)));

        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @PostMapping("/handleSubmit")
    // This handler method intercepts the POST request
    public String submitForm(Grade grade) {
        // Spring boot is going to create a grade object using the empty constructor in Grade class
        // Then, it's going to use the setters to update every single field inside of this grade object with payload from the POST request
        
        // System.out.println(grade); // For testing purposes after addeding the toString method

        int index = getGradeIndex(grade.getId());
        if (index == -1) {
            studentGrades.add(grade);    
        } else {
            // if the grade already exists, we want to update it
            studentGrades.set(index, grade);

        }

        // Redirect to all grades after submission bc submitSubmit doesn't handle rendering of new data
        return "redirect:/grades";
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) return i;
        }
        return -1; // anything, representing index not found 
    }
    
}

