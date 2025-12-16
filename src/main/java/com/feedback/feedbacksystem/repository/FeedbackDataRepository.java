package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.FeedbackData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackDataRepository extends JpaRepository<FeedbackData, Long> {
    
    // Get all feedback for a specific teacher (for the Admin Dashboard)
    List<FeedbackData> findByFacultyId(Long facultyId);
}