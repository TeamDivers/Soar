package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.dto.tag.response.TagListResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

/*
특정 Portfolio 조회 요청에 대한 응답 DTO
 */
@Getter
public class PortfolioResponseDto {

  private Long portfolioId;
  private Long member_id;
  private String title;
  private String description;
  private String category;
  private Integer bookmark;

  private List<ProjectResponseDto> projects = new ArrayList<>();
  private List<PortfolioReviewResponseDto> reviews = new ArrayList<>();
  private TagListResponseDto tags;

  // TODO : projects 만 dto로 받는 상황 해결
  public PortfolioResponseDto(Portfolio portfolio, Member member,
      List<ProjectResponseDto> projectList,
      List<PortfolioReview> reviewList, List<Tag> tagList, Integer bookmark) {
    this.portfolioId = portfolio.getId();
    this.member_id = member.getId();
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.bookmark = bookmark;

    if (reviewList != null) {
      this.reviews = reviewList.stream()
          .map(review -> PortfolioReviewResponseDto.builder()
              .member(member)
              .review(review)
              .portfolio(portfolio)
              .build())
          .collect(Collectors.toList());
    }

    if (projects != null) {
      projects = projectList;
    }

    this.tags = TagListResponseDto.builder()
        .tagList(tagList)
        .build();
  }
}
