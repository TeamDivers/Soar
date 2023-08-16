package com.konkuk.soar.portfolio.dto.portfolio.response;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.dto.tag.response.TagListResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "포트폴리오 단락 조회 시 응답하는 response body dto")
public class PortfolioOverviewDto {

  @Schema(description = "포트폴리오 id")
  private Long portfolioId;
  @Schema(description = "포트폴리오를 작성한 회원 id")
  private Long memberId;
  @Schema(description = "포트폴리오 제목")
  private String title;
  @Schema(description = "포트폴리오 설명")
  private String description;
  @Schema(description = "포트폴리오 카테고리")
  private String category;
  @Schema(description = "해당 포트폴리오의 북마크 수")
  private Integer bookmark;
  @Schema(description = "해당 포트폴리오의 등수")
  private Integer rank;
  @Schema(description = "해당 포트폴리오의 별점 평균")
  private Float score;

  @Schema(description = "해당 포트폴리오의 태그 리스트")
  private TagListResponseDto tags;

  @Builder
  public PortfolioOverviewDto(Portfolio portfolio, Integer rank, Float score, Member member,
      List<Tag> tagList, Integer bookmark) {

    this.portfolioId = portfolio.getId();
    this.memberId = member.getId();
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.bookmark = bookmark;
    this.rank = rank;
    this.score = score;
    this.tags = TagListResponseDto.builder()
        .tagList(tagList)
        .build();
  }
}