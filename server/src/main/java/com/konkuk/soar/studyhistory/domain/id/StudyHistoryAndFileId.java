package com.konkuk.soar.studyhistory.domain.id;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class StudyHistoryAndFileId implements Serializable {
    private StudyHistory studyHistory;
    private File file;
}
