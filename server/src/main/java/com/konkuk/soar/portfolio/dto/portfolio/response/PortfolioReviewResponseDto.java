package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioReviewResponseDto {

  private Long memberId;
  private Long portfolioId;
  private Float expertiseScore;
  private Float differenceScore;
  private Float perfectionScore;
  private String comment;

  @Builder
  public PortfolioReviewResponseDto(Member member, Portfolio portfolio, PortfolioReview review) {
    this.memberId = member.getId();
    this.portfolioId = portfolio.getId();
    this.expertiseScore = review.getExpertiseScore();
    this.differenceScore = review.getDifferenceScore();
    this.perfectionScore = review.getPerfectionScore();
    this.comment = review.getComment();
  }
}
