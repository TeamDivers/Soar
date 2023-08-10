package com.konkuk.soar.studyhistory.domain;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.studyhistory.domain.id.StudyHistoryAndTagId;
import jakarta.persistence.*;
import lombok.*;

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
            studyHistory.getTagList().add(this);
        }

        this.tag = tag;
        if (!tag.getStudyHistoryTagList().contains(this)) {
            tag.getStudyHistoryTagList().add(this);
        }
    }
}
