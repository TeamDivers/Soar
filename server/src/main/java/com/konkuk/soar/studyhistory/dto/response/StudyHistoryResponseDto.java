package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHistoryResponseDto {

  private Long id;
  private String content;
  private boolean isPublic;
  private String category;
  private String tagName;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  private Long memberId;
  private String timelapseURL;
  private List<String> files = new ArrayList<>();

  @Builder
  public StudyHistoryResponseDto(StudyHistory history, Member member, File timelapseFile,
      List<File> fileList,
      Tag tag) {
    this.id = history.getId();
    this.content = history.getContent();
    this.isPublic = history.getIsPublic();
    this.category = history.getCategory();
    this.startDate = history.getStartDate();
    this.endDate = history.getEndDate();
    this.memberId = member.getId();
    this.tagName = tag.getName();
    timelapseURL = null;
    if (timelapseFile != null) {
      timelapseURL = timelapseFile.getUrl();
    }

    files = fileList.stream()
        .map(f -> f.getUrl())
        .collect(Collectors.toList());
  }
}
