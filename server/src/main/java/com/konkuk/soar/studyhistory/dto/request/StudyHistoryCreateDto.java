package com.konkuk.soar.studyhistory.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class StudyHistoryCreateDto {

  private String content;
  private Boolean isPublic;
  private String category;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  private String tagName;

  private Long memberId;
  // TODO : files

  @Builder
  public StudyHistoryCreateDto(String content, Boolean isPublic, String category,
      LocalDateTime startDate, LocalDateTime endDate, String tagName, Long memberId) {
    this.content = content;
    this.isPublic = isPublic;
    this.category = category;
    this.startDate = startDate;
    this.endDate = endDate;
    this.tagName = tagName;
    this.memberId = memberId;
  }
}
