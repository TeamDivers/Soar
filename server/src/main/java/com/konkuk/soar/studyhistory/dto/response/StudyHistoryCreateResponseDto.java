package com.konkuk.soar.studyhistory.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyHistoryCreateResponseDto {

  private Long id;

  @Builder
  public StudyHistoryCreateResponseDto(Long id) {
    this.id = id;
  }
}
