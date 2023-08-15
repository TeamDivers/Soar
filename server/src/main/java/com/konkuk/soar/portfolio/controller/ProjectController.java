package com.konkuk.soar.portfolio.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectOverviewDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import com.konkuk.soar.portfolio.service.ProjectService;
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
public class ProjectController {

  private final ProjectService projectService;

  @GetMapping
  public BaseResponse<List<ProjectResponseDto>> getProjectListByPortfolioId(@RequestParam Long portfolioId) {
    // TODO : type으로 Overview/ResponseDto 구분
    List<ProjectResponseDto> result = projectService.getProjectListByPortfolioId(
        portfolioId);
    return BaseResponse.success(result);
  }

  @GetMapping("{projectId}")
  public BaseResponse<ProjectResponseDto> getProjectById(@PathVariable Long projectId) {
    ProjectResponseDto result = projectService.getProjectById(projectId);
    return BaseResponse.success(result);
  }

  @PostMapping
  public BaseResponse<ProjectOverviewDto> createProject(
      @RequestBody ProjectCreateDto dto) {
    ProjectOverviewDto result = projectService.createProject(dto);
    return BaseResponse.success(result);
  }
}
