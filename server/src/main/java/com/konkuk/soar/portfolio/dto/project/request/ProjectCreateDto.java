package com.konkuk.soar.portfolio.dto.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ProjectCreateDto {

  private String title;
  private String category;
  private String role;
  private String description;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  private String background;

  @Setter
  private Long portfolioId;
  private List<FileCreateDto> files;
  private List<Long> histories;

}
