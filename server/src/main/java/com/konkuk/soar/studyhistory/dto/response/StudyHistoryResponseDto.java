package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "학습 기록 상세 조회 시 response body dto")
public class StudyHistoryResponseDto {

  @Schema(name = "학습 기록 id")
  private Long id;
  @Schema(name = "학습한 내용")
  private String content;
  @Schema(name = "학습 기록 공개 여부")
  private boolean isPublic;
  @Schema(name = "학습 기록 카테고리")
  private String category;
  @Schema(name = "학습 태그")
  private String tagName;
  @Schema(name = "학습 시작 기간")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @Schema(name = "학습 종료 기간")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  @Schema(name = "학습 기록을 작성한 회원 id")
  private Long memberId;
  @Schema(name = "학습 기록 타임랩스")
  private String timelapseURL;
  @Schema(name = "학습 기록에 첨부한 파일 리스트")
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
