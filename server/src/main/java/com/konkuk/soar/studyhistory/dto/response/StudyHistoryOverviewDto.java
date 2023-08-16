package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "학습 기록 단락 조회 시 응답하는 response body dto")
public class StudyHistoryOverviewDto {

  @Schema(description = "학습 기록 id")
  private Long id;
  @Schema(description = "학습한 내용")
  private String content;
  @Schema(description = "학습 기록을 작성한 회원 id")
  private Long memberId;
  @Schema(description = "학습 태그")
  private String tagName;
  @Schema(description = "학습 시작 기간")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @Schema(description = "학습 종료 기간")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;

  @Builder
  public StudyHistoryOverviewDto(StudyHistory history, Tag tag, Member member) {
    this.id = history.getId();
    this.content = history.getContent();
    this.tagName = tag.getName();
    this.startDate = history.getStartDate();
    this.endDate = history.getEndDate();

    this.memberId = member.getId();
  }
}
