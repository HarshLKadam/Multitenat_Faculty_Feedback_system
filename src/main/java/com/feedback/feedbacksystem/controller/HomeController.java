package com.feedback.feedbacksystem.controller;

import com.feedback.feedbacksystem.entity.Faculty;
import com.feedback.feedbacksystem.entity.School;
import com.feedback.feedbacksystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FeedbackService feedbackService;

    // 1. Landing Page: Show list of Schools
    @GetMapping("/")
    public String home(Model model) {
        List<School> schools = feedbackService.getAllSchools();
        model.addAttribute("schools", schools);
        return "index"; // We will create index.html next
    }

    // 2. Feedback Form: User selected a school, now show faculty list
    @GetMapping("/feedback-form")
    public String showFeedbackForm(@RequestParam("schoolId") Long schoolId, Model model) {
        
        School school = feedbackService.getSchoolById(schoolId);
        List<Faculty> facultyList = feedbackService.getFacultyBySchool(schoolId);

        model.addAttribute("school", school);
        model.addAttribute("facultyList", facultyList);
        
        return "feedback_form"; // We will create feedback_form.html next
    }

    // 3. Handle Submission
    @PostMapping("/submit-feedback")
    public String submitFeedback(
            @RequestParam("schoolId") Long schoolId,
            @RequestParam("facultyId") Long facultyId,
            @RequestParam(value = "studentEmail", required = false) String studentEmail,
            @RequestParam("teaching") int teaching,
            @RequestParam("punctuality") int punctuality,
            @RequestParam("behavior") int behavior,
            @RequestParam("comments") String comments,
            RedirectAttributes redirectAttributes) {

        // Call the Service to process the logic
        String result = feedbackService.submitFeedback(schoolId, facultyId, studentEmail, teaching, punctuality, behavior, comments);

        if (result.equals("Success")) {
            return "redirect:/success";
        } else {
            // If error, go back to form and show error message
            redirectAttributes.addFlashAttribute("error", result);
            return "redirect:/feedback-form?schoolId=" + schoolId;
        }
    }

    // 4. Success Page
    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}