package com.konkuk.soar.studyhistory.service;

import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface StudyHistoryService {

  StudyHistoryOverviewDto createStudyHistory(StudyHistoryCreateDto dto);

  StudyHistoryOverviewDto createStudyHistory(StudyHistoryCreateDto dto, MultipartFile timelapse,
      List<MultipartFile> files);

  StudyHistoryResponseDto getStudyHistoryById(Long historyId);

  StudyHistoryCalendarDto getStudyHistoryCalendar(Long memberId, Integer year, Integer month);

  List<StudyHistoryOverviewDto> getStudyHistoryListByMember(Long memberId, OptionType option);

  StudyHistory addHistoryToProject(Long historyId, Project project);

  Optional<StudyHistory> findById(Long historyId);

  void deleteStudyHistory(Long historyId);
}