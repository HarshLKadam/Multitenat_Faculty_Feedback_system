package com.feedback.feedbacksystem.service;

import com.feedback.feedbacksystem.entity.*;
import com.feedback.feedbacksystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private SchoolRepository schoolRepo;
    
    @Autowired
    private FacultyRepository facultyRepo;
    
    @Autowired
    private VoteTrackingRepository voteRepo;
    
    @Autowired
    private FeedbackDataRepository feedbackRepo;

    // --- Helper Methods for Dropdowns ---

    public List<School> getAllSchools() {
        return schoolRepo.findAll();
    }

    public List<Faculty> getFacultyBySchool(Long schoolId) {
        return facultyRepo.findBySchoolId(schoolId);
    }
    
    public School getSchoolById(Long id) {
        return schoolRepo.findById(id).orElse(null);
    }
    
 // --- ADMIN MODULE: School Registration & Login ---

    public School registerSchool(String name, String emailDomain, String password, boolean isRestricted) {
        School school = new School();
        school.setName(name);
        school.setEmailDomain(emailDomain);
        school.setAdminPassword(password);
        school.setEmailRestricted(isRestricted);
        return schoolRepo.save(school);
    }

    public School loginSchool(String name, String password) {
        // Find school by name (In a real app, use email/unique ID)
        // Note: For simplicity, we assume school names are unique here
        List<School> schools = schoolRepo.findAll();
        for (School s : schools) {
            if (s.getName().equalsIgnoreCase(name) && s.getAdminPassword().equals(password)) {
                return s;
            }
        }
        return null; // Login failed
    }

    // --- ADMIN MODULE: Add Faculty ---

    public void addFaculty(Long schoolId, String facultyName, String subject) {
        School school = schoolRepo.findById(schoolId).orElse(null);
        if (school != null) {
            Faculty faculty = new Faculty();
            faculty.setName(facultyName);
            faculty.setSubject(subject);
            faculty.setSchool(school);
            facultyRepo.save(faculty);
        }
    }
    
 // Retrieve data
    public List<FeedbackData> getFeedbackByFaculty(Long facultyId) {
        return feedbackRepo.findByFacultyId(facultyId);
    }

    // --- THE CORE LOGIC: submitting feedback ---
    
    @Transactional // Ensures both saves happen, or neither happens (Rollback safety)
    public String submitFeedback(Long schoolId, Long facultyId, String studentEmail, 
                                 int teaching, int punctuality, int behavior, String comments) {
        
        // 1. Get the School Configuration
        Optional<School> schoolOpt = schoolRepo.findById(schoolId);
        if (schoolOpt.isEmpty()) return "Error: School not found";
        School school = schoolOpt.get();

        // 2. Security Check: Is this school restricted?
        if (school.isEmailRestricted()) {
            
            // Check A: Is the email empty?
            if (studentEmail == null || studentEmail.trim().isEmpty()) {
                return "Error: This school requires a valid email.";
            }

            // Check B: Does email match the domain? (e.g., must end in @mit.edu)
            if (school.getEmailDomain() != null && !studentEmail.endsWith(school.getEmailDomain())) {
                return "Error: Invalid Email Domain. You must use an @" + school.getEmailDomain() + " email.";
            }

            // Check C: Has this email already voted for THIS faculty?
            boolean alreadyVoted = voteRepo.existsByStudentEmailAndFacultyId(studentEmail, facultyId);
            if (alreadyVoted) {
                return "Error: You have already submitted feedback for this faculty member.";
            }

            // If we pass all checks, Record the Vote in the Tracking Table
            VoteTracking tracking = new VoteTracking();
            tracking.setStudentEmail(studentEmail);
            tracking.setSchool(school);
            tracking.setFaculty(facultyRepo.getById(facultyId)); // Get faculty reference
            voteRepo.save(tracking);
        }

        // 3. Save the Anonymous Feedback (The "Ballot")
        // Note: We do NOT save the studentEmail here.
        FeedbackData feedback = new FeedbackData();
        feedback.setFaculty(facultyRepo.getById(facultyId));
        feedback.setTeachingRating(teaching);
        feedback.setPunctualityRating(punctuality);
        feedback.setBehaviorRating(behavior);
        feedback.setComments(comments);
        
        feedbackRepo.save(feedback);

        return "Success";
    }
}