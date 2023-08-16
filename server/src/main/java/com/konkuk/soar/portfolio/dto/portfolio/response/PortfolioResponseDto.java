package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.dto.tag.response.TagListResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
특정 Portfolio 조회 요청에 대한 응답 DTO
 */
@Getter
@NoArgsConstructor
@Schema(description = "포트폴리오 상세 조회 시 response body dto")
public class PortfolioResponseDto {

  @Schema(description = "포트폴리오 id")
  private Long portfolioId;
  @Schema(description = "포트폴리오 작성 회원 id")
  private Long memberId;
  @Schema(description = "포트폴리오 제목")
  private String title;
  @Schema(description = "포트폴리오 설명")
  private String description;
  @Schema(description = "포트폴리오 카테고리")
  private String category;
  @Schema(description = "해당 포트폴리오 북마크 수")
  private Integer bookmark;
  @Schema(description = "해당 포트폴리오의 등수")
  private Integer rank;
  @Schema(description = "해당 포트폴리오의 별점 평균")
  @JsonFormat()
  private Double score;
  @Schema(description = "포트폴리오 배경 색")
  private String background;
  @Schema(description = "썸네일 주소")
  private String thumbnailURL;

  @Schema(description = "해당 포트폴리오에 들어있는 프로젝트 리스트")
  private List<ProjectResponseDto> projects = new ArrayList<>();
  @Schema(description = "해당 포트폴리오에 달려있는 리뷰들")
  private List<PortfolioReviewResponseDto> reviews = new ArrayList<>();
  @Schema(description = "해당 포트폴리오의 태그 리스트")
  private TagListResponseDto tags;

  // TODO : projects 만 dto로 받는 상황 해결
  @Builder
  public PortfolioResponseDto(Portfolio portfolio, Member member,
      List<ProjectResponseDto> projectList, Integer rank, Float score,
      List<PortfolioReview> reviewList, String thumbnailURL, List<Tag> tagList, Integer bookmark) {

    this.portfolioId = portfolio.getId();
    this.memberId = member.getId();
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.background = portfolio.getBackground();
    this.bookmark = bookmark;
    this.rank = rank;
    this.score = Math.round(score * 10.0) / 10.0;
    this.thumbnailURL = thumbnailURL;

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