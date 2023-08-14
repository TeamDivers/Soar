package com.konkuk.soar.portfolio.domain.portfolio;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndTagId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "portfolio_tags")
@IdClass(PortfolioAndTagId.class)
public class PortfolioTag {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pt_portfolio_id")
  private Portfolio portfolio;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pt_tag_id")
  private Tag tag;

  @Builder
  public PortfolioTag(Portfolio portfolio, Tag tag) {
    this.portfolio = portfolio;
    if (!portfolio.getTagList().contains(this)) {
      portfolio.getTagList().add(this);
    }

    this.tag = tag;
    if (!tag.getPortfolioTagList().contains(this)) {
      tag.getPortfolioTagList().add(this);
    }
  }
}
