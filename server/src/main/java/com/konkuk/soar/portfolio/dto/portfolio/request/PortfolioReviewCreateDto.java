package com.konkuk.soar.portfolio.dto.portfolio.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "포트폴리오 리뷰 생성 시 필요한 request body dto")
public class PortfolioReviewCreateDto {

  private Long portfolioId;
  private Long memberId;
  private Float expertiseScore;
  private Float differenceScore;
  private Float perfectionScore;
}
