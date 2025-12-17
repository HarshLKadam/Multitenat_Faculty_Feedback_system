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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    // === SECTION 1: SUBJECT KNOWLEDGE ===
    private int ratingTeaching;
    private int ratingSubjectDepth;
    private int ratingLearningObjCoverage;
    private int ratingRevisionEffectiveness;
    private int ratingConceptualThinking;

    // === SECTION 2: COMMUNICATION SKILLS ===
    private int ratingCommunicationSkills;
    private int ratingVoiceClarity;
    private int ratingInteractionLevel;
    private int ratingQuestionHandling;
    private int ratingLanguageProficiency;
    private int ratingEngagementLevel;

    // === SECTION 3: TEACHING METHODOLOGY ===
    private int ratingMethodology;
    private int ratingProblemSolving;
    private int ratingBlackboardUsage;
    private int ratingPptUsage;
    private int ratingLecturePreparedness;
    private int ratingConsistency;

    // === SECTION 4: CLASSROOM MANAGEMENT ===
    private int ratingPunctuality;
    private int ratingTimeManagement;
    private int ratingDiscipline;
    private int ratingControl;
    private int ratingAttentionManagement;

    // === SECTION 5: BEHAVIOR & ATTITUDE ===
    private int ratingPoliteness;
    private int ratingRespect;
    private int ratingApproachability;
    private int ratingPatience;
    private int ratingEncouragingAttitude;
    private int ratingNonDiscriminatory;
    private int ratingEthicalPractices;
    private int ratingCommitment;

    // === SECTION 6: ASSESSMENT & EVALUATION ===
    private int ratingAssessmentFairness;
    private int ratingMarkingTransparency;
    private int ratingTimelyEvaluation;
    private int ratingFeedbackQuality;

    // === SECTION 7: STUDENT SUPPORT ===
    private int ratingAvailabilityOutside;
    private int ratingDoubtClearing;
    private int ratingAcademicGuidance;
    private int ratingProjectGuidance;
    private int ratingExamPrepSupport;
    private int ratingMentorshipQuality;

    // === SECTION 8: COURSE CONTENT ===
    private int ratingSyllabusCompletion;
    private int ratingExamOrientation;
    private int ratingIndustryRelevance;
    private int ratingUpdatedContent;
    private int ratingReferenceQuality;

    // === SECTION 9: OPEN TEXT FIELDS ===
    @Column(columnDefinition = "TEXT")
    private String textStrengths;
    @Column(columnDefinition = "TEXT")
    private String textImprovements;
    @Column(columnDefinition = "TEXT")
    private String textSuggestions;
    public Long getId() {
		return id;
	}
    
    @Column(columnDefinition = "TEXT")
    private String textAdditionalComments;

    @CreationTimestamp
    @Column(name = "submission_time", updatable = false)
    private LocalDateTime submissionTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getRatingTeaching() {
		return ratingTeaching;
	}

	public void setRatingTeaching(int ratingTeaching) {
		this.ratingTeaching = ratingTeaching;
	}

	public int getRatingSubjectDepth() {
		return ratingSubjectDepth;
	}

	public void setRatingSubjectDepth(int ratingSubjectDepth) {
		this.ratingSubjectDepth = ratingSubjectDepth;
	}

	public int getRatingLearningObjCoverage() {
		return ratingLearningObjCoverage;
	}

	public void setRatingLearningObjCoverage(int ratingLearningObjCoverage) {
		this.ratingLearningObjCoverage = ratingLearningObjCoverage;
	}

	public int getRatingRevisionEffectiveness() {
		return ratingRevisionEffectiveness;
	}

	public void setRatingRevisionEffectiveness(int ratingRevisionEffectiveness) {
		this.ratingRevisionEffectiveness = ratingRevisionEffectiveness;
	}

	public int getRatingConceptualThinking() {
		return ratingConceptualThinking;
	}

	public void setRatingConceptualThinking(int ratingConceptualThinking) {
		this.ratingConceptualThinking = ratingConceptualThinking;
	}

	public int getRatingCommunicationSkills() {
		return ratingCommunicationSkills;
	}

	public void setRatingCommunicationSkills(int ratingCommunicationSkills) {
		this.ratingCommunicationSkills = ratingCommunicationSkills;
	}

	public int getRatingVoiceClarity() {
		return ratingVoiceClarity;
	}

	public void setRatingVoiceClarity(int ratingVoiceClarity) {
		this.ratingVoiceClarity = ratingVoiceClarity;
	}

	public int getRatingInteractionLevel() {
		return ratingInteractionLevel;
	}

	public void setRatingInteractionLevel(int ratingInteractionLevel) {
		this.ratingInteractionLevel = ratingInteractionLevel;
	}

	public int getRatingQuestionHandling() {
		return ratingQuestionHandling;
	}

	public void setRatingQuestionHandling(int ratingQuestionHandling) {
		this.ratingQuestionHandling = ratingQuestionHandling;
	}

	public int getRatingLanguageProficiency() {
		return ratingLanguageProficiency;
	}

	public void setRatingLanguageProficiency(int ratingLanguageProficiency) {
		this.ratingLanguageProficiency = ratingLanguageProficiency;
	}

	public int getRatingEngagementLevel() {
		return ratingEngagementLevel;
	}

	public void setRatingEngagementLevel(int ratingEngagementLevel) {
		this.ratingEngagementLevel = ratingEngagementLevel;
	}

	public int getRatingMethodology() {
		return ratingMethodology;
	}

	public void setRatingMethodology(int ratingMethodology) {
		this.ratingMethodology = ratingMethodology;
	}

	public int getRatingProblemSolving() {
		return ratingProblemSolving;
	}

	public void setRatingProblemSolving(int ratingProblemSolving) {
		this.ratingProblemSolving = ratingProblemSolving;
	}

	public int getRatingBlackboardUsage() {
		return ratingBlackboardUsage;
	}

	public void setRatingBlackboardUsage(int ratingBlackboardUsage) {
		this.ratingBlackboardUsage = ratingBlackboardUsage;
	}

	public int getRatingPptUsage() {
		return ratingPptUsage;
	}

	public void setRatingPptUsage(int ratingPptUsage) {
		this.ratingPptUsage = ratingPptUsage;
	}

	public int getRatingLecturePreparedness() {
		return ratingLecturePreparedness;
	}

	public void setRatingLecturePreparedness(int ratingLecturePreparedness) {
		this.ratingLecturePreparedness = ratingLecturePreparedness;
	}

	public int getRatingConsistency() {
		return ratingConsistency;
	}

	public void setRatingConsistency(int ratingConsistency) {
		this.ratingConsistency = ratingConsistency;
	}

	public int getRatingPunctuality() {
		return ratingPunctuality;
	}

	public void setRatingPunctuality(int ratingPunctuality) {
		this.ratingPunctuality = ratingPunctuality;
	}

	public int getRatingTimeManagement() {
		return ratingTimeManagement;
	}

	public void setRatingTimeManagement(int ratingTimeManagement) {
		this.ratingTimeManagement = ratingTimeManagement;
	}

	public int getRatingDiscipline() {
		return ratingDiscipline;
	}

	public void setRatingDiscipline(int ratingDiscipline) {
		this.ratingDiscipline = ratingDiscipline;
	}

	public int getRatingControl() {
		return ratingControl;
	}

	public void setRatingControl(int ratingControl) {
		this.ratingControl = ratingControl;
	}

	public int getRatingAttentionManagement() {
		return ratingAttentionManagement;
	}

	public void setRatingAttentionManagement(int ratingAttentionManagement) {
		this.ratingAttentionManagement = ratingAttentionManagement;
	}

	public int getRatingPoliteness() {
		return ratingPoliteness;
	}

	public void setRatingPoliteness(int ratingPoliteness) {
		this.ratingPoliteness = ratingPoliteness;
	}

	public int getRatingRespect() {
		return ratingRespect;
	}

	public void setRatingRespect(int ratingRespect) {
		this.ratingRespect = ratingRespect;
	}

	public int getRatingApproachability() {
		return ratingApproachability;
	}

	public void setRatingApproachability(int ratingApproachability) {
		this.ratingApproachability = ratingApproachability;
	}

	public int getRatingPatience() {
		return ratingPatience;
	}

	public void setRatingPatience(int ratingPatience) {
		this.ratingPatience = ratingPatience;
	}

	public int getRatingEncouragingAttitude() {
		return ratingEncouragingAttitude;
	}

	public void setRatingEncouragingAttitude(int ratingEncouragingAttitude) {
		this.ratingEncouragingAttitude = ratingEncouragingAttitude;
	}

	public int getRatingNonDiscriminatory() {
		return ratingNonDiscriminatory;
	}

	public void setRatingNonDiscriminatory(int ratingNonDiscriminatory) {
		this.ratingNonDiscriminatory = ratingNonDiscriminatory;
	}

	public int getRatingEthicalPractices() {
		return ratingEthicalPractices;
	}

	public void setRatingEthicalPractices(int ratingEthicalPractices) {
		this.ratingEthicalPractices = ratingEthicalPractices;
	}

	public int getRatingCommitment() {
		return ratingCommitment;
	}

	public void setRatingCommitment(int ratingCommitment) {
		this.ratingCommitment = ratingCommitment;
	}

	public int getRatingAssessmentFairness() {
		return ratingAssessmentFairness;
	}

	public void setRatingAssessmentFairness(int ratingAssessmentFairness) {
		this.ratingAssessmentFairness = ratingAssessmentFairness;
	}

	public int getRatingMarkingTransparency() {
		return ratingMarkingTransparency;
	}

	public void setRatingMarkingTransparency(int ratingMarkingTransparency) {
		this.ratingMarkingTransparency = ratingMarkingTransparency;
	}

	public int getRatingTimelyEvaluation() {
		return ratingTimelyEvaluation;
	}

	public void setRatingTimelyEvaluation(int ratingTimelyEvaluation) {
		this.ratingTimelyEvaluation = ratingTimelyEvaluation;
	}

	public int getRatingFeedbackQuality() {
		return ratingFeedbackQuality;
	}

	public void setRatingFeedbackQuality(int ratingFeedbackQuality) {
		this.ratingFeedbackQuality = ratingFeedbackQuality;
	}

	public int getRatingAvailabilityOutside() {
		return ratingAvailabilityOutside;
	}

	public void setRatingAvailabilityOutside(int ratingAvailabilityOutside) {
		this.ratingAvailabilityOutside = ratingAvailabilityOutside;
	}

	public int getRatingDoubtClearing() {
		return ratingDoubtClearing;
	}

	public void setRatingDoubtClearing(int ratingDoubtClearing) {
		this.ratingDoubtClearing = ratingDoubtClearing;
	}

	public int getRatingAcademicGuidance() {
		return ratingAcademicGuidance;
	}

	public void setRatingAcademicGuidance(int ratingAcademicGuidance) {
		this.ratingAcademicGuidance = ratingAcademicGuidance;
	}

	public int getRatingProjectGuidance() {
		return ratingProjectGuidance;
	}

	public void setRatingProjectGuidance(int ratingProjectGuidance) {
		this.ratingProjectGuidance = ratingProjectGuidance;
	}

	public int getRatingExamPrepSupport() {
		return ratingExamPrepSupport;
	}

	public void setRatingExamPrepSupport(int ratingExamPrepSupport) {
		this.ratingExamPrepSupport = ratingExamPrepSupport;
	}

	public int getRatingMentorshipQuality() {
		return ratingMentorshipQuality;
	}

	public void setRatingMentorshipQuality(int ratingMentorshipQuality) {
		this.ratingMentorshipQuality = ratingMentorshipQuality;
	}

	public int getRatingSyllabusCompletion() {
		return ratingSyllabusCompletion;
	}

	public void setRatingSyllabusCompletion(int ratingSyllabusCompletion) {
		this.ratingSyllabusCompletion = ratingSyllabusCompletion;
	}

	public int getRatingExamOrientation() {
		return ratingExamOrientation;
	}

	public void setRatingExamOrientation(int ratingExamOrientation) {
		this.ratingExamOrientation = ratingExamOrientation;
	}

	public int getRatingIndustryRelevance() {
		return ratingIndustryRelevance;
	}

	public void setRatingIndustryRelevance(int ratingIndustryRelevance) {
		this.ratingIndustryRelevance = ratingIndustryRelevance;
	}

	public int getRatingUpdatedContent() {
		return ratingUpdatedContent;
	}

	public void setRatingUpdatedContent(int ratingUpdatedContent) {
		this.ratingUpdatedContent = ratingUpdatedContent;
	}

	public int getRatingReferenceQuality() {
		return ratingReferenceQuality;
	}

	public void setRatingReferenceQuality(int ratingReferenceQuality) {
		this.ratingReferenceQuality = ratingReferenceQuality;
	}

	public String getTextStrengths() {
		return textStrengths;
	}

	public void setTextStrengths(String textStrengths) {
		this.textStrengths = textStrengths;
	}

	public String getTextImprovements() {
		return textImprovements;
	}

	public void setTextImprovements(String textImprovements) {
		this.textImprovements = textImprovements;
	}

	public String getTextSuggestions() {
		return textSuggestions;
	}

	public void setTextSuggestions(String textSuggestions) {
		this.textSuggestions = textSuggestions;
	}

	public String getTextAdditionalComments() {
		return textAdditionalComments;
	}

	public void setTextAdditionalComments(String textAdditionalComments) {
		this.textAdditionalComments = textAdditionalComments;
	}

	public LocalDateTime getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(LocalDateTime submissionTime) {
		this.submissionTime = submissionTime;
	}

	
    
}