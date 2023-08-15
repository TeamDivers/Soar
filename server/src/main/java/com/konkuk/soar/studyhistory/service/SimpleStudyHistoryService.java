package com.konkuk.soar.studyhistory.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.enums.FileType;
import com.konkuk.soar.common.service.TagService;
import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.service.MemberService;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import com.konkuk.soar.studyhistory.repository.StudyHistoryRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleStudyHistoryService implements StudyHistoryService {

  private final StudyHistoryRepository studyHistoryRepository;
  private final MemberService memberService;
  private final TagService tagService;
  @Override
  @Transactional
  public StudyHistoryOverviewDto createStudyHistory(StudyHistoryCreateDto dto) {
    Member member = memberService.findById(dto.getMemberId())
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);

    StudyHistory studyHistory = StudyHistory.builder()
        .category(dto.getCategory())
        .isPublic(dto.getIsPublic())
        .content(dto.getContent())
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .member(member)
        .build();

    studyHistoryRepository.save(studyHistory);

    Tag tag = tagService.addTagToStudyHistory(studyHistory, dto.getTagName());
    // TODO : files
    return getOverview(studyHistory, tag);
  }

  @Override
  @Transactional
  public StudyHistoryResponseDto getStudyHistoryById(Long historyId) {
    StudyHistory studyHistory = studyHistoryRepository.findById(historyId)
        .orElseThrow(() -> NotFoundException.STUDY_HISTORY_NOT_FOUND);
    return getResponseDto(studyHistory);
  }

  @Override
  @Transactional
  public StudyHistoryCalendarDto getStudyHistoryCalendar(Long memberId, Integer year,
      Integer month) {
    LocalDateTime start = LocalDateTime.of(LocalDate.of(year, month, 1), LocalTime.MIN);
    int lastDay = YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();
    LocalDateTime end = LocalDateTime.of(LocalDate.of(year, month, lastDay), LocalTime.MAX);

    List<StudyHistory> historyList = studyHistoryRepository.findByStartDateBetweenAndMemberId(start,
        end, memberId);

    List<StudyHistoryOverviewDto> dtoList = historyList.stream()
        .map(history -> getOverview(history, history.getTagList().get(0).getTag()))
        .toList();

    return StudyHistoryCalendarDto.builder()
        .year(year)
        .month(month)
        .dtoList(dtoList)
        .build();
  }

  @Override
  public List<StudyHistoryOverviewDto> getStudyHistoryListByMember(Long memberId, OptionType option,
      Integer size) {

    List<StudyHistory> res = null;

    switch (option) {
      case NEWEST:
        res = studyHistoryRepository.findByMemberIdOrderByCreateAtDesc(memberId,
            Pageable.ofSize(size));
        break;
      case OLDEST:
        res = studyHistoryRepository.findByMemberIdOrderByCreateAtAsc(memberId,
            Pageable.ofSize(size));
        break;
      case PUBLIC:
        res = studyHistoryRepository.findByMemberIdAndIsPublic(memberId, true,
            Pageable.ofSize(size));
        break;
      case PRIVATE:
        res = studyHistoryRepository.findByMemberIdAndIsPublic(memberId, false,
            Pageable.ofSize(size));
        break;
      case RANK:
      default:
        res = studyHistoryRepository.findByMemberId(memberId, Pageable.ofSize(size));
        break;
    }

    if (res != null) {
      return res.stream()
          .map(history -> getOverview(history, unwrapTag(history)))
          .toList();
    }

    throw new RuntimeException();
  }

  private StudyHistoryOverviewDto getOverview(StudyHistory history, Tag tag) {
    Member member = history.getMember();
    return StudyHistoryOverviewDto.builder()
        .member(member)
        .history(history)
        .tag(tag)
        .build();
  }
  private StudyHistoryResponseDto getResponseDto(StudyHistory studyHistory) {

    Member member = studyHistory.getMember();
    List<Tag> tagList = studyHistory.getTagList().stream()
        .map(StudyHistoryTag::getTag)
        .toList();
    List<File> fileList = studyHistory.getFileList().stream()
        .map(StudyHistoryFile::getFile)
        .filter(file -> !FileType.of(file.getType()).equals(FileType.TIMELAPSE))
        .toList();

    File timelapse = studyHistory.getFileList().stream()
        .map(StudyHistoryFile::getFile)
        .filter(file -> FileType.of(file.getType()).equals(FileType.TIMELAPSE))
        .findFirst()
        .orElse(null);

    return StudyHistoryResponseDto.builder()
        .history(studyHistory)
        .member(member)
        .tag(tagList.get(0))
        .fileList(fileList)
        .timelapseFile(timelapse)
        .build();
  }

  private Tag unwrapTag(StudyHistory history) {
    List<StudyHistoryTag> tagList = history.getTagList();
    if (tagList.isEmpty()) {
      // TODO : Custom Exception
      throw new IllegalStateException("history doesn't have tag");
    }
    return tagList.get(0).getTag();
  }

}
