package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHistoryResponseDto {

  private Long id;
  private String type;
  private String content;
  private boolean isPublic;
  private String category;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  private Long memberId;
  private String timelapseURL;
  private List<String> files;
  private List<String> tags;

  @Builder
  public StudyHistoryResponseDto(StudyHistory history, Member member, File timelapseFile,
      List<File> fileList,
      List<Tag> tagList) {
    this.id = history.getId();
    this.type = history.getType();
    this.content = history.getContent();
    this.isPublic = history.getIsPublic();
    this.category = history.getCategory();
    this.startDate = history.getStartDate();
    this.endDate = history.getEndDate();
    this.memberId = member.getId();
    timelapseURL = timelapseFile.getUrl();
    files = fileList.stream()
        .map(f -> f.getUrl())
        .collect(Collectors.toList());
    tags = tagList.stream()
        .map(tag -> tag.getName())
        .collect(Collectors.toList());
  }
}
