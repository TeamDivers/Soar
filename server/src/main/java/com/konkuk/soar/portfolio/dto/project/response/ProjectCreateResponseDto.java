package com.konkuk.soar.portfolio.dto.project.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProjectCreateResponseDto {

  private Long id;

  @Builder
  public ProjectCreateResponseDto(Long id) {
    this.id = id;
  }
}
