package com.feedback.feedbacksystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "email_domain")
    private String emailDomain;

    @Column(name = "is_email_restricted", nullable = false)
    private boolean emailRestricted;

    @Column(name = "admin_password", nullable = false)
    private String adminPassword;

    public School() {}

    public School(Long id, String name, String emailDomain, boolean emailRestricted, String adminPassword) {
        this.id = id;
        this.name = name;
        this.emailDomain = emailDomain;
        this.emailRestricted = emailRestricted;
        this.adminPassword = adminPassword;
    }

    // --- MANUAL GETTERS AND SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmailDomain() { return emailDomain; }
    public void setEmailDomain(String emailDomain) { this.emailDomain = emailDomain; }

    public boolean isEmailRestricted() { return emailRestricted; }
    public void setEmailRestricted(boolean emailRestricted) { this.emailRestricted = emailRestricted; }

    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
}