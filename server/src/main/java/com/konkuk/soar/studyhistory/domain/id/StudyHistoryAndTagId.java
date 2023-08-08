package com.konkuk.soar.studyhistory.domain.id;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class StudyHistoryAndTagId implements Serializable {
    private StudyHistory studyHistory;
    private Tag tag;
}
