package com.konkuk.soar.studyhistory.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudyHistoryCreateDto {

  private Long id;

  @Builder
  public StudyHistoryCreateDto(Long id) {
    this.id = id;
  }
}
