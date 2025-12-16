package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // Find all faculty members belonging to a specific school ID
    List<Faculty> findBySchoolId(Long schoolId);
}