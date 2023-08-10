package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StudyHistoryMetaResponseDto {

    private Long id;
    private String type;
    private String content;
    private Long memberId;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    @Builder
    public StudyHistoryMetaResponseDto(StudyHistory history, Member member) {
        this.id = history.getId();
        this.type = history.getType();
        this.content = history.getContent();
        this.category = history.getCategory();
        this.startDate = history.getStartDate();
        this.endDate = history.getEndDate();

        this.memberId = member.getId();
    }
}
