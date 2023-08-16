package com.konkuk.soar.portfolio.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectOverviewDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import com.konkuk.soar.portfolio.service.ProjectService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Tag(name = "Project", description = "프로젝트 관련 API Document")
public class ProjectController {

  private final ProjectService projectService;

  @Operation(summary = "프로젝트 단락 조회", description = "해당 포트폴리오에 있는 프로젝트 리스트를 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 리스트 조회 성공", content = @Content(schema = @Schema(implementation = ProjectResponseDto.class)))
  })
  @GetMapping
  public BaseResponse<List<ProjectResponseDto>> getProjectListByPortfolioId(
      @RequestParam Long portfolioId) {
    // TODO : type으로 Overview/ResponseDto 구분
    List<ProjectResponseDto> result = projectService.getProjectListByPortfolioId(
        portfolioId);
    return BaseResponse.success(result);
  }

  @Operation(summary = "프로젝트 상세 조회", description = "프로젝트를 상세 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 프로젝트 상세 조회 성공", content = @Content(schema = @Schema(implementation = ProjectResponseDto.class)))
  })
  @GetMapping("{projectId}")
  public BaseResponse<ProjectResponseDto> getProjectById(@PathVariable Long projectId) {
    ProjectResponseDto result = projectService.getProjectById(projectId);
    return BaseResponse.success(result);
  }

  @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "정상적으로 프로젝트 작성 성공", content = @Content(schema = @Schema(implementation = ProjectOverviewDto.class)))
  })
  @PostMapping
  public BaseResponse<ProjectOverviewDto> createProject(
      @RequestBody ProjectCreateDto dto) {
    ProjectOverviewDto result = projectService.createProject(dto);
    return BaseResponse.success(result);
  }
}
