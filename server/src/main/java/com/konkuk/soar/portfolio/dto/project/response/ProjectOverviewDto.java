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
@Schema(description = "@Schema(description")
public class ProjectOverviewDto {

  @Schema(description = "프로젝트 id")
  private Long projectId;
  @Schema(description = "해당 프로젝트가 속해있는 포트폴리오 id")
  private Long portfolioId;
  @Schema(description = "프로젝트 제목")
  private String title;
  @Schema(description = "프로젝트 카테고리")
  private String category;
  @Schema(description = "프로젝트 시작 날짜")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @Schema(description = "프로젝트 종료 날짜")
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
