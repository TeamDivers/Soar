package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class ProjectAndStudyHistoryId implements Serializable {

  private Project project;
  private StudyHistory studyHistory;
}
