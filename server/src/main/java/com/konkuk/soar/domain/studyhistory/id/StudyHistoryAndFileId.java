package com.konkuk.soar.domain.studyhistory.id;

import com.konkuk.soar.domain.common.File;
import com.konkuk.soar.domain.studyhistory.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class StudyHistoryAndFileId implements Serializable {
    private StudyHistory studyHistory;
    private File file;
}
