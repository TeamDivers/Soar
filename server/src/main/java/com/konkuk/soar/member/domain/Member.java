package com.konkuk.soar.member.domain;

import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioBookmark;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

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

  @NotBlank
  @Length(max = 300)
  @Column(name = "member_email")
  private String email;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<Portfolio> portfolioList = new ArrayList<>();

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<PortfolioBookmark> bookmarkList = new ArrayList<>();

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<PortfolioReview> reviewList = new ArrayList<>();

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<StudyHistory> studyHistoryList = new ArrayList<>();

  @Builder
  public Member(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
