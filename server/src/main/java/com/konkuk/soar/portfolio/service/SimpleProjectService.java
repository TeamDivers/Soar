package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.service.AwsS3Service;
import com.konkuk.soar.common.service.FileService;
import com.konkuk.soar.global.exception.NotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SimpleProjectService implements ProjectService {

  private final ProjectRepository projectRepository;

  private final PortfolioRepository portfolioRepository;
  private final StudyHistoryService studyHistoryService;
  private final FileService fileService;
  private final AwsS3Service awsS3Service;
  private final String FILE_BASE_URL = "/project/";

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
        .portfolio(portfolio)
        .build();

    projectRepository.save(project);

    for (Long historyId : dto.getHistories()) {
      studyHistoryService.addHistoryToProject(historyId, project);
    }

    return getOverview(project);
  }

  @Override
  @Transactional
  public ProjectOverviewDto createProject(ProjectCreateDto dto, List<MultipartFile> files) {
    ProjectOverviewDto projectResult = createProject(dto);

    Project prj = projectRepository.findById(projectResult.getProjectId())
        .orElseThrow(() -> NotFoundException.PROJECT_NOT_FOUND);
    Long memberId = prj.getPortfolio().getMember().getId();
    for (MultipartFile file : files) {
      FileResponseDto fileResult = awsS3Service.uploadFile(
          memberId + FILE_BASE_URL + prj.getId() + "/files",
          file);

      File filEntity = fileService.findById(fileResult.getFileId())
          .orElseThrow(() -> NotFoundException.FILE_NOT_FOUND);
      fileService.addFileToProject(filEntity, prj);
    }
    return projectResult;
  }

  @Override
  public void deleteProject(Long projectId) {

  }

  @Override
  @Transactional
  public void deleteProjects(Long portfolioId) {
    projectRepository.deleteProjectsByPortfolioId(portfolioId);
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