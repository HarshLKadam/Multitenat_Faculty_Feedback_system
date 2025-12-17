package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    // CHANGED: Find faculty by COURSE ID, not School ID
    List<Faculty> findByCourseId(Long courseId);
}