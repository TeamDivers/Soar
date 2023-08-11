package com.konkuk.soar.studyhistory.domain.id;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class StudyHistoryAndTagId implements Serializable {

  private StudyHistory studyHistory;
  private Tag tag;
}
