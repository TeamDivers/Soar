package com.konkuk.soar.domain.portfolio.id;

import com.konkuk.soar.domain.portfolio.project.Project;
import com.konkuk.soar.domain.studyhistory.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class ProjectAndStudyHistoryId implements Serializable {
    private Project project;
    private StudyHistory studyHistory;
}
