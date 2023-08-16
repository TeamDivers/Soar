package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "포트폴리오의 리뷰에 대한 response body dto")
public class PortfolioReviewResponseDto {

  @Schema(description = "해당 포트폴리오에 평가를 남긴 회원 id")
  private Long memberId;
  @Schema(description = "포트폴리오 id")
  private Long portfolioId;
  @Schema(description = "포트폴리오 전문성 별점")
  private Float expertiseScore;
  @Schema(description = "포트폴리오 차별성 별점")
  private Float differenceScore;
  @Schema(description = "포트폴리오 완성도 별점")
  private Float perfectionScore;
  @Schema(description = "해당 포트폴리오에 대한 코멘트")
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
