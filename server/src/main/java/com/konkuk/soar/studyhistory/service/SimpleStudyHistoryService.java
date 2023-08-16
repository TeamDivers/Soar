package com.konkuk.soar.studyhistory.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.service.AwsS3Service;
import com.konkuk.soar.common.service.FileService;
import com.konkuk.soar.common.service.TagService;
import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.service.MemberService;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.repository.project.ProjectStudyHistoryRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import com.konkuk.soar.studyhistory.repository.StudyHistoryFileRepository;
import com.konkuk.soar.studyhistory.repository.StudyHistoryRepository;
import com.konkuk.soar.studyhistory.repository.StudyHistoryTagRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleStudyHistoryService implements StudyHistoryService {

  private final StudyHistoryRepository studyHistoryRepository;
  private final StudyHistoryFileRepository studyHistoryFileRepository;
  private final StudyHistoryTagRepository studyHistoryTagRepository;

  private final ProjectStudyHistoryRepository projectStudyHistoryRepository;
  private final MemberService memberService;
  private final TagService tagService;
  private final FileService fileService;
  private final AwsS3Service awsS3Service;
  private final String FILE_BASE_URL = "/study/";

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

    return getOverview(studyHistory, tag);
  }

  @Override
  @Transactional
  public StudyHistoryOverviewDto createStudyHistory(StudyHistoryCreateDto dto,
      MultipartFile timelapse, List<MultipartFile> files) {
    StudyHistoryOverviewDto historyResult = this.createStudyHistory(dto);

    FileResponseDto timelapseResult = awsS3Service.uploadFile(
        historyResult.getMemberId() + FILE_BASE_URL + historyResult.getId() + "/timelapse",
        timelapse);

    File timelapseFile = fileService.findById(timelapseResult.getFileId())
        .orElseThrow(() -> NotFoundException.FILE_NOT_FOUND);

    StudyHistory history = this.findById(historyResult.getId())
        .orElseThrow(() -> NotFoundException.STUDY_HISTORY_NOT_FOUND);
    fileService.addFileToStudyHistory(timelapseFile, history);

    for (MultipartFile file : files) {
      FileResponseDto fileResult = awsS3Service.uploadFile(
          historyResult.getMemberId() + FILE_BASE_URL + historyResult.getMemberId() + "/files",
          file);
      File f = fileService.findById(fileResult.getFileId())
          .orElseThrow(() -> NotFoundException.FILE_NOT_FOUND);

      StudyHistory h = this.findById(historyResult.getId())
          .orElseThrow(() -> NotFoundException.STUDY_HISTORY_NOT_FOUND);
      fileService.addFileToStudyHistory(f, h);
    }
    return historyResult;
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
  @Transactional
  public List<StudyHistoryOverviewDto> getStudyHistoryListByMember(Long memberId, OptionType option) {

    List<StudyHistory> res = null;

    switch (option) {
      case NEWEST:
        log.info("member.id={}", memberId);
        res = studyHistoryRepository.findByMemberIdOrderByCreateAtDesc(memberId);
        break;
      case OLDEST:
        res = studyHistoryRepository.findByMemberIdOrderByCreateAtAsc(memberId);
        break;
      case PUBLIC:
        res = studyHistoryRepository.findByMemberIdAndIsPublic(memberId, true);
        break;
      case PRIVATE:
        res = studyHistoryRepository.findByMemberIdAndIsPublic(memberId, false);
        break;
      case RANK:
      default:
        res = studyHistoryRepository.findByMemberId(memberId);
        break;
    }

    if (res != null) {
      return res.stream()
          .map(history -> {
            log.info("history.id={}", history.getId());
            return getOverview(history, unwrapTag(history));
          })
          .toList();
    }

    throw new RuntimeException();
  }

  @Override
  @Transactional
  public StudyHistory addHistoryToProject(Long historyId, Project project) {
    StudyHistory studyHistory = studyHistoryRepository.findById(historyId)
        .orElseThrow(() -> NotFoundException.STUDY_HISTORY_NOT_FOUND);

    projectStudyHistoryRepository.save(ProjectStudyHistory.builder()
        .project(project)
        .studyHistory(studyHistory)
        .build());

    return studyHistory;
  }

  @Override
  public Optional<StudyHistory> findById(Long historyId) {
    return studyHistoryRepository.findById(historyId);
  }

  protected StudyHistoryOverviewDto getOverview(StudyHistory history, Tag tag) {
    Member member = history.getMember();
    return StudyHistoryOverviewDto.builder()
        .member(member)
        .history(history)
        .tag(tag)
        .build();
  }

  /**
   * @param historyId
   * @진행순서 StudyHistoryTag -> StudyHistoryFile -> ProjectStudyHistory -> StudyHistory 순으로 삭제 진행
   */
  @Override
  @Transactional
  public void deleteStudyHistory(Long historyId) {
    studyHistoryTagRepository.deleteAllByStudyHistoryId(historyId);
    studyHistoryFileRepository.deleteAllByStudyHistoryId(historyId);
    projectStudyHistoryRepository.deleteAllByStudyHistoryId(historyId);
    studyHistoryRepository.deleteById(historyId);
  }

  protected StudyHistoryResponseDto getResponseDto(StudyHistory studyHistory) {

    Member member = studyHistory.getMember();
    List<Tag> tagList = studyHistory.getTagList().stream()
        .map(StudyHistoryTag::getTag)
        .toList();
    List<File> fileList = studyHistory.getFileList().stream()
        .map(StudyHistoryFile::getFile)
        .filter(file -> !file.getUrl().contains("timelapse"))
        .toList();

    File timelapse = studyHistory.getFileList().stream()
        .map(StudyHistoryFile::getFile)
        .filter(file -> file.getUrl().contains("timelapse"))
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

  protected Tag unwrapTag(StudyHistory history) {
    List<StudyHistoryTag> tagList = history.getTagList();
    if (tagList.isEmpty()) {
      // TODO : Custom Exception
      throw new IllegalStateException("history doesn't have tag");
    }
    return tagList.get(0).getTag();
  }

}