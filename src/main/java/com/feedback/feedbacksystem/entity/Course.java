package com.feedback.feedbacksystem.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName; // e.g., "MCA"

    @Column(nullable = false)
    private String semester;   // e.g., "Sem 1"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private School school;

    // --- MANUAL GETTERS AND SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
}