package com.konkuk.soar.domain.portfolio.id;

import com.konkuk.soar.domain.common.File;
import com.konkuk.soar.domain.portfolio.project.Project;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class ProjectAndFileId implements Serializable {
    private Project project;
    private File file;
}
