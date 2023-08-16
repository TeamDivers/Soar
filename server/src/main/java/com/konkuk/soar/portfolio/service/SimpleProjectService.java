package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectOverviewDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.service.StudyHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimpleProjectService implements ProjectService{

  private final ProjectRepository projectRepository;

  private final PortfolioRepository portfolioRepository;
  private final StudyHistoryService studyHistoryService;

  @Override
  @Transactional
  public ProjectResponseDto getProjectById(Long projectId) {
    // TODO : Exception 바꾸기
    Project project = projectRepository.findById(projectId)
        .orElseThrow(RuntimeException::new);
    return getResponseDto(project);
  }

  @Override
  @Transactional
  public List<ProjectResponseDto> getProjectListByPortfolioId(Long portfolioId) {
    List<Project> res = projectRepository.findByPortfolioId(portfolioId);
    return res.stream()
        .map(this::getResponseDto)
        .toList();
  }

  @Override
  @Transactional
  public List<ProjectOverviewDto> getProjectOverviewListByPortfolioId(Long portfolioId) {
    List<Project> res = projectRepository.findByPortfolioId(portfolioId);
    return res.stream()
        .map(this::getOverview)
        .toList();
  }

  @Override
  @Transactional
  public ProjectOverviewDto createProject(ProjectCreateDto dto) {
    // TODO : RuntimeException -> Custom으로 바꾸기
    Portfolio portfolio = portfolioRepository.findById(dto.getPortfolioId()).
        orElseThrow(RuntimeException::new);

    Project project = Project.builder()
        .title(dto.getTitle())
        .category(dto.getCategory())
        .role(dto.getRole())
        .description(dto.getDescription())
        .startDate(dto.getStartDate())
        .endDate(dto.getEndDate())
        .designBackground(dto.getBackground())
        .portfolio(portfolio)
        .build();

    projectRepository.save(project);

    for (Long historyId : dto.getHistories()) {
      studyHistoryService.addHistoryToProject(historyId, project);
    }

    return getOverview(project);
    // TODO : file save
  }

  protected ProjectResponseDto getResponseDto(Project project) {
    Portfolio portfolio = project.getPortfolio();
    List<File> fileList = project.getProjectFiles().stream()
        .map(ProjectFile::getFile)
        .toList();
    List<StudyHistory> studyHistoryList = project.getStudyHistoryList().stream()
        .map(ProjectStudyHistory::getStudyHistory)
        .toList();

    return ProjectResponseDto.builder()
        .project(project)
        .member(portfolio.getMember())
        .portfolio(portfolio)
        .fileList(fileList)
        .studyHistoryList(studyHistoryList)
        .build();
  }

  protected ProjectOverviewDto getOverview(Project project) {
    return ProjectOverviewDto.builder()
        .project(project)
        .portfolio(project.getPortfolio())
        .build();
  }
}