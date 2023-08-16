package com.konkuk.soar.studyhistory.repository;

import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyHistoryRepository extends JpaRepository<StudyHistory, Long> {

  List<StudyHistory> findByStartDateBetweenAndMemberId(LocalDateTime start, LocalDateTime end,Long memberId);
  List<StudyHistory> findByEndDateBetweenAndMemberId(LocalDateTime start, LocalDateTime end,Long memberId);
  //TODO : CreateAt으로 할지, StartDate로 할지
  List<StudyHistory> findByMemberIdOrderByCreateAtAsc(Long memberId);
  List<StudyHistory> findByMemberIdOrderByCreateAtDesc(Long memberId);
  List<StudyHistory> findByMemberIdAndIsPublic(Long memberId, Boolean isPublic);

  List<StudyHistory> findByMemberId(Long memberId);


}