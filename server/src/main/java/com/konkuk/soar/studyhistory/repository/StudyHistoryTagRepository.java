package com.konkuk.soar.studyhistory.repository;

import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.domain.id.StudyHistoryAndTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyHistoryTagRepository extends
    JpaRepository<StudyHistoryTag, StudyHistoryAndTagId> {

    void deleteAllByStudyHistoryId(Long studyHistoryId);
}
