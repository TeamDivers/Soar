package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class ProjectAndStudyHistoryId implements Serializable {

    private Project project;
    private StudyHistory studyHistory;
}
