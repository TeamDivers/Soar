package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleFileService implements FileService {

  private final FileRepository fileRepository;

  @Override
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
  public FileResponseDto findById(Long id) {
    return null;
  }
}
