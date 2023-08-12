package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.dto.tag.response.TagListResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioOverviewDto {

  private Long portfolioId;
  private Long memberId;
  private String title;
  private String description;
  private String category;
  private Integer bookmark;

  private TagListResponseDto tags;

  @Builder
  public PortfolioOverviewDto(Portfolio portfolio, Member member,
      List<Tag> tagList, Integer bookmark) {
    this.portfolioId = portfolio.getId();
    this.memberId = member.getId();
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.bookmark = bookmark;

    this.tags = TagListResponseDto.builder()
        .tagList(tagList)
        .build();
  }
}
