package com.konkuk.soar.portfolio.domain.project;

import com.konkuk.soar.portfolio.domain.id.ProjectAndStudyHistoryId;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "project_study_histories")
@IdClass(ProjectAndStudyHistoryId.class)
public class ProjectStudyHistory {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "psh_project_id")
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
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
}
