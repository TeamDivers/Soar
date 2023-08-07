package com.konkuk.soar.domain.portfolio;

import com.konkuk.soar.domain.member.Member;
import com.konkuk.soar.domain.portfolio.id.PortfolioAndMemberId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "portfolio_bookmarks")
@IdClass(PortfolioAndMemberId.class)
public class PortfolioBookmark {
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pb_portfolio_id")
    private Portfolio portfolio;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    @PreRemove
    private void preRemove() {
        if (portfolio != null) {
            portfolio.getBookmarkList().remove(this);
            portfolio = null;
        }

        if (member != null) {
            member.getBookmarkList().remove(this);
            member = null;
        }
    }
}
