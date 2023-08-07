package com.konkuk.soar.domain.portfolio.project;

import com.konkuk.soar.domain.portfolio.id.ProjectAndStudyHistoryId;
import com.konkuk.soar.domain.studyhistory.StudyHistory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "project_study_histories")
@IdClass(ProjectAndStudyHistoryId.class)
public class ProjectStudyHistory {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "psh_project_id")
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "psh_study_history_id")
    private StudyHistory studyHistory;

    @Builder
    public ProjectStudyHistory(Project project, StudyHistory studyHistory) {
        this.project = project;
        if (!project.getStudyHistoryList().contains(this)) {
            project.getStudyHistoryList().add(this);
        }

        this.studyHistory = studyHistory;
        if (!studyHistory.getProjectStudyHistoryList().contains(this)) {
            studyHistory.getProjectStudyHistoryList().add(this);
        }
    }

    @PreRemove
    private void preRemove() {
        if (project != null) {
            project.getStudyHistoryList().remove(this);
            project = null;
        }
        if (studyHistory != null) {
            studyHistory.getProjectStudyHistoryList().remove(this);
            studyHistory = null;
        }
    }
}
