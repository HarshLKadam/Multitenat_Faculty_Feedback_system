package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Find all courses for a specific school
    List<Course> findBySchoolId(Long schoolId);
}