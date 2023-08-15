package com.konkuk.soar.studyhistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "학습 기록을 달력으로 보기 위한 response body dto")
public class StudyHistoryCalendarDto {

  @Schema(name = "년도")
  private Integer year;
  @Schema(name = "월")
  private Integer month;
  @Schema(name = "<날짜, 그 날짜에 한 학습 기록>의 Map")
  private Map<Integer, List<StudyHistoryOverviewDto>> dayMap;

  @Builder
  public StudyHistoryCalendarDto(List<StudyHistoryOverviewDto> dtoList, int year, int month) {
    this.year = year;
    this.month = month;
    dayMap = new HashMap<>();

    int startDay, endDay;
    for (StudyHistoryOverviewDto history : dtoList) {
      startDay = history.getStartDate().getDayOfMonth();
      endDay = history.getEndDate().getDayOfMonth();

      if (!dayMap.containsKey(startDay)) {
        dayMap.put(startDay, new ArrayList<>());
      }

      List<StudyHistoryOverviewDto> list = dayMap.get(startDay);
      list.add(history);

      if (startDay != endDay) {
        if (!dayMap.containsKey(endDay)) {
          dayMap.put(endDay, new ArrayList<>());
        }

        List<StudyHistoryOverviewDto> elist = dayMap.get(endDay);
        elist.add(history);
      }
    }
  }
}
