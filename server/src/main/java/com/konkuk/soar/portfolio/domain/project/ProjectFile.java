package com.konkuk.soar.portfolio.domain.project;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.portfolio.domain.id.ProjectAndFileId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "project_files")
@IdClass(ProjectAndFileId.class)
public class ProjectFile {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pf_project_id")
  private Project project;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pf_file_id")
  private File file;

  @Builder
  public ProjectFile(Project project, File file) {
    this.project = project;
    if (!project.getProjectFiles().contains(this)) {
      project.getProjectFiles().add(this);
    }

    this.file = file;
    if (!file.getProjectFileList().contains(this)) {
      file.getProjectFileList().add(this);
    }
  }
}
