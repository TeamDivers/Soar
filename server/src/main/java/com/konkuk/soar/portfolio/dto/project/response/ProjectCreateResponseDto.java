package com.konkuk.soar.portfolio.dto.project.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCreateDto {

  private Long id;

  @Builder
  public ProjectCreateDto(Long id) {
    this.id = id;
  }
}
