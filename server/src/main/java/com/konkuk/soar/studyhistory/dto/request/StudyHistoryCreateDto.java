package com.konkuk.soar.studyhistory.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Schema(description = "학습기록 생성 시 필요한 request body dto")
public class StudyHistoryCreateDto {

  @Schema(name = "학습 기록 내용")
  private String content;
  @Schema(name = "학습 기록 공개 여부")
  private Boolean isPublic;
  @Schema(name = "학습 기록 카테고리")
  private String category;
  @Schema(name = "학습 시작 기간")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @Schema(name = "학습 종료 기간")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  @Schema(name = "학습 태그")
  private String tagName;

  @Schema(name = "학습한 회원 id")
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
