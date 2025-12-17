package com.feedback.feedbacksystem.controller;

import com.feedback.feedbacksystem.entity.*;
import com.feedback.feedbacksystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
public class HomeController {

    @Autowired
    private FeedbackService feedbackService;

    // 1. Landing Page: Show Schools AND Ratings
    @GetMapping("/")
    public String home(Model model) {
        List<School> schools = feedbackService.getAllSchools();
        
        // --- THIS WAS MISSING ---
        // Calculate average rating for each school
        Map<Long, Double> schoolRatings = new HashMap<>();
        
        for (School s : schools) {
            // This calls the service method we created in the previous step
            Double avg = feedbackService.getSchoolAverageRating(s.getId());
            schoolRatings.put(s.getId(), avg);
        }
        // ------------------------

        model.addAttribute("schools", schools);
        model.addAttribute("schoolRatings", schoolRatings); // Sending the map to HTML
        return "index";
    }

    // 2. Select Course Page
    @GetMapping("/select-course")
    public String selectCourse(@RequestParam("schoolId") Long schoolId, Model model) {
        School school = feedbackService.getSchoolById(schoolId);
        List<Course> courses = feedbackService.getCoursesBySchool(schoolId);
        
        model.addAttribute("school", school);
        model.addAttribute("courses", courses);
        return "select_course";
    }

    // 3. Feedback Form
    @GetMapping("/feedback-form")
    public String showFeedbackForm(@RequestParam("courseId") Long courseId, Model model) {
        Course course = feedbackService.getCourseById(courseId);
        if (course == null) return "redirect:/";

        List<Faculty> facultyList = feedbackService.getFacultyByCourse(courseId);

        model.addAttribute("course", course);
        model.addAttribute("school", course.getSchool());
        model.addAttribute("facultyList", facultyList);
        model.addAttribute("feedbackData", new FeedbackData()); 
        
        return "feedback_form";
    }

    // 4. Handle Submission
    @PostMapping("/submit-feedback")
    public String submitFeedback(
            @RequestParam("schoolId") Long schoolId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("facultyId") Long facultyId,
            @RequestParam(value = "studentEmail", required = false) String studentEmail,
            @ModelAttribute FeedbackData feedbackData, 
            RedirectAttributes redirectAttributes) {

        String result = feedbackService.submitFeedback(schoolId, courseId, facultyId, studentEmail, feedbackData);

        if (result.equals("Success")) {
            return "redirect:/success";
        } else {
            redirectAttributes.addFlashAttribute("error", result);
            return "redirect:/feedback-form?courseId=" + courseId;
        }
    }
 // 5. NEW PAGE: Show Faculty Ratings for a specific School
    @GetMapping("/school-ratings")
    public String showSchoolFacultyRatings(@RequestParam("schoolId") Long schoolId, Model model) {
        School school = feedbackService.getSchoolById(schoolId);
        if (school == null) return "redirect:/";

        List<Faculty> facultyList = feedbackService.getAllFacultyForSchool(schoolId);
        
        // Calculate Rating for each Faculty
        Map<Long, Double> facultyRatings = new HashMap<>();
        for (Faculty f : facultyList) {
            Double avg = feedbackService.getFacultyAverageRating(f.getId());
            facultyRatings.put(f.getId(), avg);
        }

        model.addAttribute("school", school);
        model.addAttribute("facultyList", facultyList);
        model.addAttribute("facultyRatings", facultyRatings);
        
        return "individual_faculty_rating"; // We will create this next
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}