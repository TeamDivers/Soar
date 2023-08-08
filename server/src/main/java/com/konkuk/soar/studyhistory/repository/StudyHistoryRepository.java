package com.konkuk.soar.studyhistory.repository;

import com.konkuk.soar.studyhistory.domain.StudyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyHistoryRepository extends JpaRepository<StudyHistory, Long> {

}
