package com.konkuk.soar.portfolio.repository.project;

import com.konkuk.soar.portfolio.domain.id.ProjectAndStudyHistoryId;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectStudyHistoryRepository extends
    JpaRepository<ProjectStudyHistory, ProjectAndStudyHistoryId> {

  void deleteProjectStudyHistoriesByProjectId(Long projectId);
  void deleteAllByProjectId(Long projectId);
  void deleteAllByStudyHistoryId(Long studyHistoryId);
}
