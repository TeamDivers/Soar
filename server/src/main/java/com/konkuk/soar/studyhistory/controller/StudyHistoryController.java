package com.konkuk.soar.studyhistory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.studyhistory.dto.request.StudyHistoryCreateDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryCalendarDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryOverviewDto;
import com.konkuk.soar.studyhistory.dto.response.StudyHistoryResponseDto;
import com.konkuk.soar.studyhistory.service.StudyHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/studyhistories")
@Tag(name = "StudyHistory", description = "학습 기록 관련 API Document")
public class StudyHistoryController {

  private final StudyHistoryService studyHistoryService;
  private final ObjectMapper objectMapper;

  @Operation(summary = "학습 기록 조회", description = "회원 id 기반 단일 학습 기록 조회.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StudyHistoryResponseDto.class)))
  })
  @GetMapping("/{historyId}")
  public BaseResponse<StudyHistoryResponseDto> getStudyHistoryById(@PathVariable Long historyId) {
    StudyHistoryResponseDto result = studyHistoryService.getStudyHistoryById(historyId);
    return BaseResponse.success(result);
  }

  @Operation(summary = "학습 기록 달력 조회", description = "학습 기록을 캘린더뷰로 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StudyHistoryCalendarDto.class)))
  })
  @GetMapping("/calendar/{memberId}")
  public BaseResponse<StudyHistoryCalendarDto> getStudyHistoryCalendar(@PathVariable Long memberId,
      @RequestParam Integer month, @RequestParam Integer year) {
    StudyHistoryCalendarDto result = studyHistoryService.getStudyHistoryCalendar(
        memberId, year, month);
    return BaseResponse.success(result);
  }

  @Operation(summary = "학습 기록 단락 조회", description = "회원 id 기반 학습 기록 리스트를 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = StudyHistoryOverviewDto.class)))
  })
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

  @Operation(summary = "학습 기록 생성", description = "학습 기록을 생성합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "OK", content = @Content(schema = @Schema(implementation = StudyHistoryOverviewDto.class)))
  })
  @PostMapping
  public BaseResponse<StudyHistoryOverviewDto> createStudyHistory(@RequestParam("studyHistory")
  String studyHistory, @RequestPart(name = "timelapse", required = false) MultipartFile timelapse,
      @RequestPart(name = "files", required = false) List<MultipartFile> files) {
    try {
      StudyHistoryCreateDto dto = objectMapper.readValue(studyHistory,
          StudyHistoryCreateDto.class);
      StudyHistoryOverviewDto historyResult = studyHistoryService.createStudyHistory(dto, timelapse,
          files);
      return BaseResponse.success(historyResult);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}