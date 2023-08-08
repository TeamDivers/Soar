package com.konkuk.soar.portfolio.repository.project;

import com.konkuk.soar.portfolio.domain.id.ProjectAndFileId;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, ProjectAndFileId> {
}
