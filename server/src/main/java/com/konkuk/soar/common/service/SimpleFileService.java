package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.repository.FileRepository;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioFile;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.portfolio.repository.PortfolioFileRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectFileRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import com.konkuk.soar.studyhistory.repository.StudyHistoryFileRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimpleFileService implements FileService {

  private final FileRepository fileRepository;
  private final StudyHistoryFileRepository studyHistoryFileRepository;
  private final ProjectFileRepository projectFileRepository;
  private final PortfolioFileRepository portfolioFileRepository;

  @Override
  @Transactional
  public FileResponseDto createFile(FileCreateDto createDto, String savedName, String url) {
    File file = File.builder()
        .type(createDto.getType())
        .originalName(createDto.getFileName())
        .savedName(savedName)
        .url(url)
        .build();

    fileRepository.save(file);

    FileResponseDto responseDto = FileResponseDto.builder()
        .file(file)
        .build();
    return responseDto;
  }

  @Override
  @Transactional
  public FileResponseDto addFileToStudyHistory(File file, StudyHistory studyHistory) {
    StudyHistoryFile saved = studyHistoryFileRepository.save(StudyHistoryFile.builder()
        .studyHistory(studyHistory)
        .file(file)
        .build());
    return FileResponseDto.builder()
        .file(saved.getFile())
        .build();
  }

  @Override
  @Transactional
  public FileResponseDto addFileToProject(File file, Project project) {
    ProjectFile saved = projectFileRepository.save(ProjectFile.builder()
        .project(project)
        .file(file)
        .build());
    return FileResponseDto.builder()
        .file(saved.getFile())
        .build();
  }

  @Override
  @Transactional
  public FileResponseDto addFileToPortfolio(File file, Portfolio portfolio) {
    PortfolioFile saved = portfolioFileRepository.save(PortfolioFile.builder()
        .portfolio(portfolio)
        .file(file)
        .build());
    return FileResponseDto.builder()
        .file(saved.getFile())
        .build();
  }

  @Override
  @Transactional
  public Optional<File> findById(Long id) {
    return fileRepository.findById(id);
  }
}
