package com.konkuk.soar.portfolio.dto.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectMetaResponseDto {

  private Long projectId;
  private Long portfolioId;
  private String title;
  private String category;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime endDate;

  @Builder
  public ProjectMetaResponseDto(Project project, Portfolio portfolio) {
    this.projectId = project.getId();
    this.portfolioId = portfolio.getId();
    this.title = project.getTitle();
    this.category = project.getCategory();
    this.startDate = project.getStartDate();
    this.endDate = project.getEndDate();
  }
}
