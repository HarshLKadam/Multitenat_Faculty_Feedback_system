package com.feedback.feedbacksystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feedback.feedbacksystem.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {
    // Check if username exists (for registration)
    boolean existsByUsername(String username);

    // Find for login
    School findByUsername(String username);
}