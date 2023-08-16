package com.konkuk.soar.studyhistory.service;

import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import java.util.List;

public interface StudyHistoryService {

  StudyHistoryOverviewDto createStudyHistory(StudyHistoryCreateDto dto);
  StudyHistoryResponseDto getStudyHistoryById(Long historyId);

  StudyHistoryCalendarDto getStudyHistoryCalendar(Long memberId, Integer year, Integer month);

  List<StudyHistoryOverviewDto> getStudyHistoryListByMember(Long memberId, OptionType option,
      Integer size);

  StudyHistory addHistoryToProject(Long historyId, Project project);
}