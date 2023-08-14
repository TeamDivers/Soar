package com.konkuk.soar.studyhistory.domain;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.studyhistory.domain.id.StudyHistoryAndFileId;
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
@Table(name = "study_history_file")
@IdClass(StudyHistoryAndFileId.class)
public class StudyHistoryFile {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shf_study_history_id")
  private StudyHistory studyHistory;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "shf_file_id")
  private File file;

  @Builder
  public StudyHistoryFile(StudyHistory studyHistory, File file) {
    this.studyHistory = studyHistory;
    if (!studyHistory.getFileList().contains(this)) {
      studyHistory.getFileList().add(this);
    }

    this.file = file;
    if (!file.getStudyHistoryFileList().contains(this)) {
      file.getStudyHistoryFileList().add(this);
    }
  }

}
