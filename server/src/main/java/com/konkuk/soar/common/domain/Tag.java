package com.konkuk.soar.common.domain;

import com.konkuk.soar.portfolio.domain.portfolio.PortfolioTag;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
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
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    @Length(max = 20)
    private String name;

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<PortfolioTag> portfolioTagList = new ArrayList<>();

    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    @Setter(AccessLevel.NONE)
    private List<StudyHistoryTag> studyHistoryTagList = new ArrayList<>();

    @Builder
    public Tag(String name) {
        this.name = name;
    }
}
