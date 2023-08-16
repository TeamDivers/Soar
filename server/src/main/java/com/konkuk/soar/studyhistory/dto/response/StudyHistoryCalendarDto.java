package com.konkuk.soar.studyhistory.dto.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyHistoryCalendarDto {

  private Integer year;
  private Integer month;
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
