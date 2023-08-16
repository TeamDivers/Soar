package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectOverviewDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import java.util.List;

public interface ProjectService {

  ProjectResponseDto getProjectById(Long projectId);
  List<ProjectResponseDto> getProjectListByPortfolioId(Long portfolioId);
  List<ProjectOverviewDto> getProjectOverviewListByPortfolioId(Long portfolioId);

  ProjectOverviewDto createProject(ProjectCreateDto dto);
}