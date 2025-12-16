package com.feedback.feedbacksystem.repository;

import com.feedback.feedbacksystem.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    // Custom method to find a school by its email domain (e.g., "mit.edu")
    Optional<School> findByEmailDomain(String emailDomain);
}