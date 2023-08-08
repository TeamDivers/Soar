package com.konkuk.soar.studyhistory.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "study_histories")
public class StudyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_history_id")
    private Long id;

    @NotBlank
    @Length(max = 20)
    @Column(name = "study_history_type")
    private String type;

    @NotBlank
    @Length(max = 1000)
    @Column(name = "study_history_content")
    private String content;

    @NotNull
    @Column(name = "study_history_is_open")
    private Boolean isPublic;

    @NotBlank
    @Length(max = 1000)
    @Column(name = "study_history_category")
    private String category;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    @Column(name = "study_history_start_date")
    private LocalDateTime startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    @Column(name = "study_history_end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "study_history_member_id")
    @Setter(AccessLevel.NONE)
    private Member member;

    @OneToMany(mappedBy = "studyHistory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<ProjectStudyHistory> projectStudyHistoryList = new ArrayList<>();

    @OneToMany(mappedBy = "studyHistory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<StudyHistoryFile> fileList = new ArrayList<>();

    @OneToMany(mappedBy = "studyHistory", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.NONE)
    private List<StudyHistoryTag> tagList = new ArrayList<>();

    @Builder
    public StudyHistory(String type, String content, Boolean isPublic, String category, LocalDateTime startDate, LocalDateTime endDate, Member member) {
        this.type = type;
        this.content = content;
        this.isPublic = isPublic;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;

        this.member = member;
        if (!member.getStudyHistoryList().contains(this)) {
            member.getStudyHistoryList().add(this);
        }
    }

    @PreRemove
    private void preRemove() {
        if (member != null) {
            member.getStudyHistoryList().remove(this);
            member = null;
        }
    }
}
