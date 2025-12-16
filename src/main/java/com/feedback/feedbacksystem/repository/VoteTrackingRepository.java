package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.VoteTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteTrackingRepository extends JpaRepository<VoteTracking, Long> {
    
    // The "Magic" Check:
    // Checks if a row exists with this Email AND this Faculty ID.
    // Returns true if they have already voted.
    boolean existsByStudentEmailAndFacultyId(String studentEmail, Long facultyId);
}