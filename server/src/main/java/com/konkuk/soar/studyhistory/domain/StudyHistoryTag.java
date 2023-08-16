package com.konkuk.soar.studyhistory.domain;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.studyhistory.domain.id.StudyHistoryAndTagId;
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
@Table(name = "study_history_tags")
@IdClass(StudyHistoryAndTagId.class)
public class StudyHistoryTag {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sht_study_history_id")
  private StudyHistory studyHistory;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sht_tag_id")
  private Tag tag;

  @Builder
  public StudyHistoryTag(StudyHistory studyHistory, Tag tag) {
    this.studyHistory = studyHistory;
    if (!studyHistory.getTagList().contains(this)) {
      tag.getStudyHistoryTagList().add(this);
    }

    this.tag = tag;
    if (!tag.getStudyHistoryTagList().contains(this)) {
      tag.getStudyHistoryTagList().add(this);
    }
  }
}
