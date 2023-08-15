package com.konkuk.soar.portfolio.dto.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCreateRequestDto {

  private String title;
  private String category;
  private String role;
  private String description;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  private String background;
  private List<Long> fileIdList;

  @Builder
  public ProjectCreateRequestDto(String title, String category, String role, String description,
      LocalDateTime startDate, LocalDateTime endDate, String background, List<Long> fileIdList) {
    this.title = title;
    this.category = category;
    this.role = role;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.background = background;
    this.fileIdList = fileIdList;
  }
}
