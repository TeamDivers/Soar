package com.konkuk.soar.portfolio.domain;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "portfolio_reviews")
@IdClass(PortfolioAndMemberId.class)
public class PortfolioReview {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_portfolio_id")
    @Setter(AccessLevel.NONE)
    private Portfolio portfolio;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_member_id")
    @Setter(AccessLevel.NONE)
    private Member member;

    @NotNull
    @Range(min = 0, max = 5)
    @Column(name = "pr_expertise_score")
    private Float expertiseScore;

    @NotNull
    @Range(min = 0, max = 5)
    @Column(name = "pr_difference_score")
    private Float differenceScore;

    @NotNull
    @Range(min = 0, max = 5)
    @Column(name = "pr_perfection_score")
    private Float perfectionScore;

    @NotBlank
    @Length(max = 1000)
    @Column(name = "pr_comment")
    private String comment;

    @Builder
    public PortfolioReview(Portfolio portfolio, Member member, Float expertiseScore, Float differenceScore, Float perfectionScore,
            String comment) {
        this.expertiseScore = expertiseScore;
        this.differenceScore = differenceScore;
        this.perfectionScore = perfectionScore;
        this.comment = comment;

        this.portfolio = portfolio;
        if (!portfolio.getReviewList().contains(this)) {
            portfolio.getReviewList().add(this);
        }
        this.member = member;
        if (!member.getReviewList().contains(this)) {
            member.getReviewList().add(this);
        }
    }
}