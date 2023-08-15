package com.konkuk.soar.portfolio.dto.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트 단락 조회 시 응답하는 response body dto")
public class ProjectOverviewDto {

  @Schema(name = "프로젝트 id")
  private Long projectId;
  @Schema(name = "해당 프로젝트가 속해있는 포트폴리오 id")
  private Long portfolioId;
  @Schema(name = "프로젝트 제목")
  private String title;
  @Schema(name = "프로젝트 카테고리")
  private String category;
  @Schema(name = "프로젝트 시작 날짜")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @Schema(name = "프로젝트 종료 날짜")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime endDate;

  @Builder
  public ProjectOverviewDto(Project project, Portfolio portfolio) {
    this.projectId = project.getId();
    this.portfolioId = portfolio.getId();
    this.title = project.getTitle();
    this.category = project.getCategory();
    this.startDate = project.getStartDate();
    this.endDate = project.getEndDate();
  }
}
