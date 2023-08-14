package com.konkuk.soar.studyhistory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHistoryResponseDto {

  private Long id;
  private String tag;
  private String content;
  private boolean isPublic;
  private String category;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime startDate;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
  private LocalDateTime endDate;
  private Long memberId;
  private String timelapseURL;
  private List<String> files = new ArrayList<>();


  //TODO: 여기서 tag를 어떻게 받아오는 것이 좋을까요?
  @Builder
  public StudyHistoryResponseDto(StudyHistory history, Member member, File timelapseFile,
      List<File> fileList, Tag tag) {
    this.id = history.getId();
    this.tag = tag.getName();
    this.content = history.getContent();
    this.isPublic = history.getIsPublic();
    this.category = history.getCategory();
    this.startDate = history.getStartDate();
    this.endDate = history.getEndDate();
    this.memberId = member.getId();

    timelapseURL = null;
    if (timelapseFile != null) {
      timelapseURL = timelapseFile.getUrl();
    }

    files = fileList.stream()
        .map(File::getUrl)
        .collect(Collectors.toList());
  }
}
