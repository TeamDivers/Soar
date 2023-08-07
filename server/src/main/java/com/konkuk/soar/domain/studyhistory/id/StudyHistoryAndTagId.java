package com.konkuk.soar.domain.studyhistory.id;

import com.konkuk.soar.domain.common.Tag;
import com.konkuk.soar.domain.studyhistory.StudyHistory;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class StudyHistoryAndTagId implements Serializable {
    private StudyHistory studyHistory;
    private Tag tag;
}
