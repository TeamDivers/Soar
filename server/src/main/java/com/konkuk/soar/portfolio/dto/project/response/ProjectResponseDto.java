package com.konkuk.soar.portfolio.dto.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryMetaResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProjectResponseDto {

  private Long projectId;
  private Long portfolioId;
  private String title;
  private String category;
  private String role;
  private String description;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  private List<StudyHistoryMetaResponseDto> studyHistories;
  private List<FileResponseDto> files;

  @Builder
  public ProjectResponseDto(Project project, Portfolio portfolio, Member member,
      List<StudyHistory> studyHistoryList,
      List<File> fileList) {
    this.projectId = project.getId();
    this.portfolioId = portfolio.getId();
    this.title = project.getTitle();
    this.category = project.getCategory();
    this.role = project.getRole();
    this.description = project.getDescription();
    this.startDate = project.getStartDate();
    this.endDate = project.getEndDate();

    if (studyHistoryList != null) {
      this.studyHistories = studyHistoryList.stream()
          .map(studyHistory -> StudyHistoryMetaResponseDto.builder()
              .member(member)
              .history(studyHistory)
              .build())
          .collect(Collectors.toList());
    }
    if (fileList != null) {
      this.files = fileList.stream()
          .map(FileResponseDto::new)
          .collect(Collectors.toList());
    }
  }
}