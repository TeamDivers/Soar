package com.konkuk.soar.member.domain;

import com.konkuk.soar.portfolio.domain.Portfolio;
import com.konkuk.soar.portfolio.domain.PortfolioBookmark;
import com.konkuk.soar.portfolio.domain.PortfolioReview;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotBlank
    @Length(max = 20)
    @Column(name = "member_name")
    private String name;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<Portfolio> portfolioList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<PortfolioBookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<PortfolioReview> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<StudyHistory> studyHistoryList = new ArrayList<>();

    @Builder
    public Member(String name) {

        this.name = name;
    }
}
