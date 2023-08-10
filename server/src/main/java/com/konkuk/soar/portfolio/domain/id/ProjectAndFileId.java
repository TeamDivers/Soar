package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.portfolio.domain.project.Project;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class ProjectAndFileId implements Serializable {

    private Project project;
    private File file;
}
