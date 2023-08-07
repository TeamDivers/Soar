package com.konkuk.soar.domain.portfolio.project;

import com.konkuk.soar.domain.common.File;
import com.konkuk.soar.domain.portfolio.id.ProjectAndFileId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "project_files")
@IdClass(ProjectAndFileId.class)
public class ProjectFile {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pf_project_id")
    private Project project;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    @PreRemove
    private void preRemove() {
        if (this.project != null) {
            project.getProjectFiles().remove(this);
            this.project = null;
        }

        if (this.file != null) {
            file.getProjectFileList().remove(this);
            this.file = null;
        }
    }
}
