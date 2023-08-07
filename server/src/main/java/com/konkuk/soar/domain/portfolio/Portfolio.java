package com.konkuk.soar.domain.portfolio;

import com.konkuk.soar.domain.member.Member;
import com.konkuk.soar.domain.portfolio.project.Project;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "portfolios")
public class Portfolio {
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "portfolio_member_id")
    @Setter(AccessLevel.NONE)
    private Member member;

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<Project> projectList = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<PortfolioBookmark> bookmarkList = new ArrayList<>();

    // TODO : REVIEW 살릴지?
    @OneToMany(mappedBy = "portfolio", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<PortfolioReview> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<PortfolioTag> tagList = new ArrayList<>();

    @Builder
    public Portfolio(String title, String description, String category, boolean isPublic, Member member) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.isPublic = isPublic;

        this.member = member;
        if (!member.getPortfolioList().contains(this)) {
            member.getPortfolioList().add(this);
        }
    }

    @PreRemove
    private void preRemove() {
        if (member != null) {
            member.getPortfolioList().remove(this);
            member = null;
        }
    }
}
