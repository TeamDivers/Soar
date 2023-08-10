package com.konkuk.soar.portfolio.domain;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
import jakarta.persistence.*;
import lombok.*;

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
