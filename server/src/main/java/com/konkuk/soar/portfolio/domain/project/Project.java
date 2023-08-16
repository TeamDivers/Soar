package com.konkuk.soar.portfolio.domain.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
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
import java.time.LocalDateTime;
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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @NotBlank
    @Length(max = 20)
    @Column(name = "project_title")
    private String title;

    @NotBlank
    @Length(max = 20)
    @Column(name = "project_category")
    private String category;

    @NotBlank
    @Length(max = 20)
    @Column(name = "project_role")
    private String role;

    @NotBlank
    @Length(max = 10000)
    @Column(name = "project_description")
    private String description;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    @Column(name = "project_start_date")
    private LocalDateTime startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    @Column(name = "project_end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_portfolio_id")
    @Setter(AccessLevel.NONE)
    private Portfolio portfolio;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<ProjectFile> projectFiles = new ArrayList<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<ProjectStudyHistory> studyHistoryList = new ArrayList<>();

    @Builder
    public Project(String title, String category, String role, String description,
            LocalDateTime startDate, LocalDateTime endDate, Portfolio portfolio) {
        this.title = title;
        this.category = category;
        this.role = role;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

        this.portfolio = portfolio;
        if (!portfolio.getProjectList().contains(this)) {
            portfolio.getProjectList().add(this);
        }
    }
}