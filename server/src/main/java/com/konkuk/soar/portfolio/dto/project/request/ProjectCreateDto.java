package com.konkuk.soar.portfolio.dto.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트를 생성할 때 필요한 request body dto")
@ToString
public class ProjectCreateDto {

  @Schema(description = "프로젝트 제목")
  private String title;
  @Schema(description = "프로젝트 카테고리")
  private String category;
  @Schema(description = "프로젝트에서 맡은 역할")
  private String role;
  @Schema(description = "프로젝트 설명")
  private String description;
  @Schema(description = "프로젝트 시작 날짜")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @Schema(description = "프로젝트 종료 날짜")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;

  @Setter
  private Long portfolioId;
  @Schema(description = "해당 프로젝트에 첨부할 학습 기록 리스트")
  private List<Long> histories;

}