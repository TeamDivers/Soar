package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCreateDto {

  private String title;
  private String description;
  private String category;
  private boolean isPublic;
  private Long memberId;

  @Builder
  public PortfolioCreateDto(Portfolio portfolio, Member member) {
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.isPublic = portfolio.isPublic();
    this.memberId = member.getId();
  }
}
