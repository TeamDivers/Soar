package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Schema(description = "포트폴리오 생성 시 필요한 request body dto")
public class PortfolioCreateDto {

  @Schema(description = "포트폴리오 제목")
  private String title;
  @Schema(description = "포트폴리오 설명")
  private String description;
  @Schema(description = "포트폴리오 카테고리")
  private String category;
  @Schema(description = "포트폴리오 공개 여부")
  private boolean isPublic;
  @Schema(description = "포트폴리오 배경 색")
  private String background;

  @Schema(description = "포트폴리오 작성 회원 id")
  private Long memberId;
  @Schema(description = "포트폴리오 태그 리스트")
  private List<String> tags = new ArrayList<>();

  @Builder
  public PortfolioCreateDto(Portfolio portfolio, Member member) {
    this.title = portfolio.getTitle();
    this.description = portfolio.getDescription();
    this.category = portfolio.getCategory();
    this.isPublic = portfolio.isPublic();
    this.background = portfolio.getBackground();
    this.memberId = member.getId();
  }
}