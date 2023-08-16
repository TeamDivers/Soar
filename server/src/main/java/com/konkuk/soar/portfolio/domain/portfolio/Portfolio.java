package com.konkuk.soar.portfolio.domain.portfolio;

import com.konkuk.soar.common.domain.BaseTime;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.project.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "portfolios")
public class Portfolio extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "portfolio_id")
  private Long id;

  @NotBlank
  @Length(max = 20)
  @Column(name = "portfolio_title")
  private String title;

  @NotBlank
  @Length(max = 1000)
  @Column(name = "portfolio_description")
  private String description;

  @NotBlank
  @Length(max = 1000)
  @Column(name = "portfolio_category")
  private String category;

  @NotNull
  @Column(name = "portfolio_is_public")
  private boolean isPublic;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio_member_id")
  @Setter(AccessLevel.NONE)
  private Member member;

  @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<PortfolioFile> fileList = new ArrayList<>();

  @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<Project> projectList = new ArrayList<>();

  @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<PortfolioBookmark> bookmarkList = new ArrayList<>();

  // TODO : REVIEW 살릴지?
  @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY)
  @Setter(AccessLevel.NONE)
  private List<PortfolioReview> reviewList = new ArrayList<>();

  @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER)
  @Setter(AccessLevel.NONE)
  private List<PortfolioTag> tagList = new ArrayList<>();

  @Builder
  public Portfolio(String title, String description, String category, boolean isPublic,
      Member member) {

    this.title = title;
    this.description = description;
    this.category = category;
    this.isPublic = isPublic;

    this.member = member;
    if (!member.getPortfolioList().contains(this)) {
      member.getPortfolioList().add(this);
    }
  }
}