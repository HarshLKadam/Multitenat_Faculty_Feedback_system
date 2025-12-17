package com.feedback.feedbacksystem.service;

import com.feedback.feedbacksystem.entity.*;
import com.feedback.feedbacksystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private SchoolRepository schoolRepo;
    
    @Autowired
    private CourseRepository courseRepo;
    
    @Autowired
    private FacultyRepository facultyRepo;
    
    @Autowired
    private VoteTrackingRepository voteRepo;
    
    @Autowired
    private FeedbackDataRepository feedbackRepo;

    // --- Helper Methods ---

    public List<School> getAllSchools() {
        return schoolRepo.findAll();
    }
    
    public School getSchoolById(Long id) {
        return schoolRepo.findById(id).orElse(null);
    }

    public List<Course> getCoursesBySchool(Long schoolId) {
        return courseRepo.findBySchoolId(schoolId);
    }
    
    public Course getCourseById(Long courseId) {
        return courseRepo.findById(courseId).orElse(null);
    }

    public List<Faculty> getFacultyByCourse(Long courseId) {
        return facultyRepo.findByCourseId(courseId);
    }
    
    public List<FeedbackData> getFeedbackByFaculty(Long facultyId) {
        return feedbackRepo.findByFacultyId(facultyId);
    }
    
    // ADMIN: Get all faculty for a school (Complex query via Java stream)
    public List<Faculty> getAllFacultyForSchool(Long schoolId) {
        List<Course> courses = courseRepo.findBySchoolId(schoolId);
        List<Faculty> allFaculty = new java.util.ArrayList<>();
        for (Course c : courses) {
            allFaculty.addAll(facultyRepo.findByCourseId(c.getId()));
        }
        return allFaculty;
    }

    // --- SUBMIT FEEDBACK LOGIC ---
    
    @Transactional
    public String submitFeedback(Long schoolId, Long courseId, Long facultyId, String studentEmail, 
                                 FeedbackData feedbackData) {
        
        // 1. Get School & Security Checks
        School school = schoolRepo.findById(schoolId).orElse(null);
        if (school == null) return "Error: School not found";

        if (school.isEmailRestricted()) {
            if (studentEmail == null || studentEmail.trim().isEmpty()) {
                return "Error: Email required.";
            }
            if (school.getEmailDomain() != null && !studentEmail.endsWith(school.getEmailDomain())) {
                return "Error: Invalid Email Domain. Use @" + school.getEmailDomain();
            }
            boolean alreadyVoted = voteRepo.existsByStudentEmailAndFacultyId(studentEmail, facultyId);
            if (alreadyVoted) {
                return "Error: You have already voted for this faculty.";
            }

            // Record Vote
            VoteTracking tracking = new VoteTracking();
            tracking.setStudentEmail(studentEmail);
            tracking.setSchool(school);
            tracking.setFaculty(facultyRepo.findById(facultyId).get());
            voteRepo.save(tracking);
        }

        // 2. Save Feedback
        feedbackData.setFaculty(facultyRepo.findById(facultyId).get());
        feedbackData.setCourse(courseRepo.findById(courseId).get());
        
        feedbackRepo.save(feedbackData);

        return "Success";
    }
    
    // --- INSTITUTE (ADMIN) MODULE ---

    // UPDATED: Now accepts 'username'
    public School registerSchool(String name, String username, String emailDomain, String password, boolean isRestricted) {
        // 1. Check if username is taken
        if (schoolRepo.existsByUsername(username)) {
            return null; // Username already exists
        }
        
        School school = new School();
        school.setName(name);
        school.setUsername(username); // Set the unique login ID
        school.setEmailDomain(emailDomain);
        school.setAdminPassword(password);
        school.setEmailRestricted(isRestricted);
        return schoolRepo.save(school);
    }

    // UPDATED: Login by 'username' instead of 'name'
    public School loginSchool(String username, String password) {
        School s = schoolRepo.findByUsername(username);
        if (s != null && s.getAdminPassword().equals(password)) {
            return s;
        }
        return null; // Login failed
    }
    
    // Admin adds Course first
    public void addCourse(Long schoolId, String name, String sem) {
        School s = schoolRepo.findById(schoolId).orElse(null);
        if(s != null) {
            Course c = new Course();
            c.setSchool(s);
            c.setCourseName(name);
            c.setSemester(sem);
            courseRepo.save(c);
        }
    }

    // Admin adds Faculty to a Course
    public void addFaculty(Long courseId, String name, String subject, String photoUrl) {
        Course c = courseRepo.findById(courseId).orElse(null);
        if (c != null) {
            Faculty f = new Faculty();
            f.setName(name);
            f.setSubject(subject);
            f.setPhotoUrl(photoUrl);
            f.setCourse(c);
            facultyRepo.save(f);
        }
    }
    
    //returns average rating for school faculty
    public Double getSchoolAverageRating(Long schoolId) {
        List<Faculty> facultyList = getAllFacultyForSchool(schoolId);
        
        if (facultyList.isEmpty()) return 0.0;

        double totalScore = 0;
        int totalVotes = 0;

        for (Faculty f : facultyList) {
            List<FeedbackData> feedbacks = feedbackRepo.findByFacultyId(f.getId());
            for (FeedbackData feed : feedbacks) {
                // We use Teaching Rating as the primary indicator, 
                // or you can average all 45 fields if you prefer heavy calculation.
                // For performance, let's use the main "Teaching" score.
                totalScore += feed.getRatingTeaching(); 
                totalVotes++;
            }
        }

        if (totalVotes == 0) return 0.0;
        
        // Print statement as per your requirements
        System.out.println("Calc Avg for School ID " + schoolId + " - Roll No:C25012 Name: Shreyash Bhosale");
        
        return totalScore / totalVotes;
    }
    
    //returns average faculty rating
    public Double getFacultyAverageRating(Long facultyId) {
        List<FeedbackData> feedbackList = feedbackRepo.findByFacultyId(facultyId);
        if (feedbackList.isEmpty()) return 0.0;

        double totalScore = 0;
        for (FeedbackData f : feedbackList) {
            // We use 'Teaching Quality' as the primary score. 
            // You can also average multiple fields here if you want a more complex score.
            totalScore += f.getRatingTeaching(); 
        }
        return totalScore / feedbackList.size();
    }
}