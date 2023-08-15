package com.konkuk.soar.portfolio.dto.portfolio.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCreateResponseDto {

  private Long id;

  @Builder
  public PortfolioCreateResponseDto(Long id) {
    this.id = id;
  }
}
