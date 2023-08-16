package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import java.util.Optional;

public interface FileService {

  FileResponseDto createFile(FileCreateDto createDto, String savedName, String url);

  FileResponseDto addFileToStudyHistory(File file, StudyHistory studyHistory);

  Optional<File> findById(Long id);

}
