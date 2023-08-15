package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PortfolioCreateDto {

  private String title;
  private String description;
  private String category;
  private boolean isPublic;

  private Long memberId;
  private List<String> tags = new ArrayList<>();

  @Builder
  public PortfolioCreateDto(Portfolio portfolio, Member member) {
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.isPublic = portfolio.isPublic();
    this.memberId = member.getId();
  }
}
