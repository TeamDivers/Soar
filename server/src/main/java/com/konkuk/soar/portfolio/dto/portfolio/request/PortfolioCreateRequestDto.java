package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCreateRequestDto {

  private String title;
  private String description;
  private String category;
  private boolean isPublic;
  private Long memberId;

  @Builder
  public PortfolioCreateRequestDto(String title, String description, String category,
      boolean isPublic, Member member) {
    this.title = title;
    this.description = description;
    this.category = category;
    this.isPublic = isPublic;
    this.memberId = member.getId();
  }
}
