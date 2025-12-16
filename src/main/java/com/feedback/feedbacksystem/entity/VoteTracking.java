package com.feedback.feedbacksystem.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote_tracking",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"student_email", "faculty_id"})})
public class VoteTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faculty_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private School school;

    @CreationTimestamp
    @Column(name = "vote_timestamp", updatable = false)
    private LocalDateTime voteTimestamp;

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public Faculty getFaculty() { return faculty; }
    public void setFaculty(Faculty faculty) { this.faculty = faculty; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }

    public LocalDateTime getVoteTimestamp() { return voteTimestamp; }
    public void setVoteTimestamp(LocalDateTime voteTimestamp) { this.voteTimestamp = voteTimestamp; }
}