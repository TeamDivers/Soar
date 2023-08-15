package com.konkuk.soar.portfolio.dto.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트 상세 조회 시 response body dto")
public class ProjectResponseDto {

    @Schema(name = "프로젝트 id")
    private Long projectId;
    @Schema(name = "해당 프로젝트가 속해있는 포트폴리오 id")
    private Long portfolioId;
    @Schema(name = "프로젝트 제목")
    private String title;
    @Schema(name = "프로젝트 카테고리")
    private String category;
    @Schema(name = "프로젝트에서 맡은 역할")
    private String role;
    @Schema(name = "프로젝트 상세 설명")
    private String description;
    @Schema(name = "프로젝트 시작 기간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @Schema(name = "프로젝트 종료 기간")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    @Schema(name = "해당 프로젝트에 첨부된 학습 기록 리스트")
    private List<StudyHistoryOverviewDto> studyHistories;
    @Schema(name = "해당 프로젝트에 첨부된 파일 리스트")
    private List<FileResponseDto> files;

    @Builder
    public ProjectResponseDto(Project project, Portfolio portfolio, Member member,
            List<StudyHistory> studyHistoryList,
            List<File> fileList) {
        this.projectId = project.getId();
        this.portfolioId = portfolio.getId();
        this.title = project.getTitle();
        this.category = project.getCategory();
        this.role = project.getRole();
        this.description = project.getDescription();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();

        if (studyHistoryList != null) {
            this.studyHistories = studyHistoryList.stream()
                    .map(studyHistory -> StudyHistoryOverviewDto.builder()
                            .member(member)
                            .tag(studyHistory.getTagList().get(0).getTag())
                            .history(studyHistory)
                            .build())
                    .collect(Collectors.toList());
        }
        if (fileList != null) {
            this.files = fileList.stream()
                    .map(FileResponseDto::new)
                    .collect(Collectors.toList());
        }
    }
}