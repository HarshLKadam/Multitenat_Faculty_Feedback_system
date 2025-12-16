package com.feedback.feedbacksystem.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_data")
public class FeedbackData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;

    @Column(name = "teaching_rating", nullable = false)
    private int teachingRating;

    @Column(name = "punctuality_rating", nullable = false)
    private int punctualityRating;

    @Column(name = "behavior_rating", nullable = false)
    private int behaviorRating;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @CreationTimestamp
    @Column(name = "submission_time", updatable = false)
    private LocalDateTime submissionTime;

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public int getTeachingRating() { return teachingRating; }
    public void setTeachingRating(int teachingRating) { this.teachingRating = teachingRating; }

    public int getPunctualityRating() { return punctualityRating; }
    public void setPunctualityRating(int punctualityRating) { this.punctualityRating = punctualityRating; }

    public int getBehaviorRating() { return behaviorRating; }
    public void setBehaviorRating(int behaviorRating) { this.behaviorRating = behaviorRating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    
    public LocalDateTime getSubmissionTime() { return submissionTime; }
    public void setSubmissionTime(LocalDateTime submissionTime) { this.submissionTime = submissionTime; }
}