package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHistoryOverviewDto {

  private Long id;
  private String content;
  private Long memberId;
  private String category;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;

  @Builder
  public StudyHistoryOverviewDto(StudyHistory history, Member member) {
    this.id = history.getId();
    this.content = history.getContent();
    this.category = history.getCategory();
    this.startDate = history.getStartDate();
    this.endDate = history.getEndDate();

    this.memberId = member.getId();
  }
}
