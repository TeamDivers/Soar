package com.konkuk.soar.studyhistory.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import com.konkuk.soar.studyhistory.service.StudyHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/studyhistories")
public class StudyHistoryController {

  private final StudyHistoryService studyHistoryService;

  @GetMapping("/{historyId}")
  public BaseResponse<StudyHistoryResponseDto> getStudyHistoryById(@PathVariable Long historyId) {
    StudyHistoryResponseDto result = studyHistoryService.getStudyHistoryById(historyId);
    return BaseResponse.success(result);
  }

  @GetMapping("/calendar/{memberId}")
  public BaseResponse<StudyHistoryCalendarDto> getStudyHistoryCalendar(@PathVariable Long memberId,
      @RequestParam Integer month, @RequestParam Integer year) {
    StudyHistoryCalendarDto result = studyHistoryService.getStudyHistoryCalendar(
        memberId, year, month);
    return BaseResponse.success(result);
  }

  @GetMapping
  public BaseResponse<List<StudyHistoryOverviewDto>> getStudyHistoryList(
      @RequestParam Long memberId,
      @RequestParam String option,
      @RequestParam(required = false, defaultValue = "5") Integer size) {
    OptionType optionType = OptionType.of(option);
    List<StudyHistoryOverviewDto> result = studyHistoryService.getStudyHistoryListByMember(
        memberId, optionType, size);
    return BaseResponse.success(result);
  }

  @PostMapping
  public BaseResponse<StudyHistoryOverviewDto> createStudyHistory(@RequestBody
  StudyHistoryCreateDto dto) {
    StudyHistoryOverviewDto res = studyHistoryService.createStudyHistory(dto);
    return BaseResponse.success(res);
  }

}
