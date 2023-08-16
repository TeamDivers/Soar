package com.konkuk.soar.portfolio.dto.portfolio;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioScore implements Comparable<PortfolioScore> {

  private Long portfolioId;
  private Float score;

  @Builder
  public PortfolioScore(Long portfolioId, Float score) {
    this.portfolioId = portfolioId;
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortfolioScore that = (PortfolioScore) o;
    return Objects.equals(portfolioId, that.portfolioId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portfolioId);
  }

  @Override
  public int compareTo(PortfolioScore o) {
    if (this.score.equals(o.score)) {
      return 0;
    } else if (this.score < o.score) {
      return 1;
    }
    return -1;
  }

}
