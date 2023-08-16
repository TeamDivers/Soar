package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public interface FileService {

  FileResponseDto createFile(FileCreateDto createDto, String savedName, String url);

  FileResponseDto addFileToStudyHistory(File file, StudyHistory studyHistory);
  FileResponseDto addFileToProject(File file, Project studyHistory);

  @Transactional
  FileResponseDto addFileToPortfolio(File file, Portfolio portfolio);

  Optional<File> findById(Long id);

}
