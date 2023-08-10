package com.konkuk.soar.studyhistory.repository;

import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import com.konkuk.soar.studyhistory.domain.id.StudyHistoryAndFileId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyHistoryFileRepository extends JpaRepository<StudyHistoryFile, StudyHistoryAndFileId> {
}
