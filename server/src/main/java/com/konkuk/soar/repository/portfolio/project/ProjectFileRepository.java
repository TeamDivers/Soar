package com.konkuk.soar.repository.portfolio.project;

import com.konkuk.soar.domain.portfolio.id.ProjectAndFileId;
import com.konkuk.soar.domain.portfolio.project.ProjectFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectFileRepository extends JpaRepository<ProjectFile, ProjectAndFileId> {
}
