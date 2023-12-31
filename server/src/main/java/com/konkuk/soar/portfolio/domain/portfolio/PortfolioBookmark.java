package com.konkuk.soar.portfolio.domain.portfolio;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
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
@Table(name = "portfolio_bookmarks")
@IdClass(PortfolioAndMemberId.class)
public class PortfolioBookmark {

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pb_portfolio_id")
  private Portfolio portfolio;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pb_member_id")
  private Member member;

  @Builder
  public PortfolioBookmark(Portfolio portfolio, Member member) {
    this.portfolio = portfolio;
    if (!portfolio.getBookmarkList().contains(this)) {
      portfolio.getBookmarkList().add(this);
    }

    this.member = member;
    if (!member.getBookmarkList().contains(this)) {
      member.getBookmarkList().add(this);
    }
  }
}
