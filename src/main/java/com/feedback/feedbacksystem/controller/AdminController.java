package com.feedback.feedbacksystem.controller;

import com.feedback.feedbacksystem.entity.Faculty;
import com.feedback.feedbacksystem.entity.School;
import com.feedback.feedbacksystem.service.FeedbackService;
import com.feedback.feedbacksystem.entity.FeedbackData;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    // 1. Show Login/Register Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin_login";
    }

    // 2. Handle Registration
    @PostMapping("/register")
    public String registerSchool(@RequestParam String name, 
                                 @RequestParam String emailDomain, 
                                 @RequestParam String password,
                                 @RequestParam(defaultValue = "false") boolean isRestricted,
                                 HttpSession session) {
        
        School school = feedbackService.registerSchool(name, emailDomain, password, isRestricted);
        session.setAttribute("schoolId", school.getId());
        return "redirect:/admin/dashboard";
    }

    // 3. Handle Login
    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, HttpSession session, Model model) {
        School school = feedbackService.loginSchool(name, password);
        if (school != null) {
            session.setAttribute("schoolId", school.getId());
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid School Name or Password");
            return "admin_login";
        }
    }

    // 4. Show Dashboard (Requires Login)
    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return "redirect:/admin/login"; // Protect the page

        School school = feedbackService.getSchoolById(schoolId);
        List<Faculty> facultyList = feedbackService.getFacultyBySchool(schoolId);

        model.addAttribute("school", school);
        model.addAttribute("facultyList", facultyList);
        return "admin_dashboard";
    }

    // 5. Add Faculty Action
    @PostMapping("/add-faculty")
    public String addFaculty(@RequestParam String name, @RequestParam String subject, HttpSession session) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId != null) {
            feedbackService.addFaculty(schoolId, name, subject);
        }
        return "redirect:/admin/dashboard";
    }

    // 6. Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
 // 7. View Feedback for a Specific Faculty
    @GetMapping("/view-feedback")
    public String viewFeedback(@RequestParam Long facultyId, HttpSession session, Model model) {
        Long schoolId = (Long) session.getAttribute("schoolId");
        if (schoolId == null) return "redirect:/admin/login";

        // Fetch the faculty to make sure they belong to this school (Security)
        // We will assume valid access for this mini-project
        
        // 1. Fetch the Faculty details (Name, Subject)
        // Note: You might need to add a simple getFacultyById method in Service if not exists
        // Or simply iterate to find name. For now, let's fetch the list.
        List<Faculty> facultyList = feedbackService.getFacultyBySchool(schoolId);
        Faculty selectedFaculty = facultyList.stream()
                .filter(f -> f.getId().equals(facultyId))
                .findFirst()
                .orElse(null);

        if (selectedFaculty == null) return "redirect:/admin/dashboard";

        // 2. Fetch all Feedback for this Faculty
        // We need to add this method to FeedbackService
        List<FeedbackData> feedbackList = feedbackService.getFeedbackByFaculty(facultyId);

        model.addAttribute("faculty", selectedFaculty);
        model.addAttribute("feedbackList", feedbackList);

        return "faculty_feedback_details"; // We will create this HTML file next
    }
}