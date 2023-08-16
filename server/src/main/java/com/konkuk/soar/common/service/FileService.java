package com.konkuk.soar.common.service;

import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;

public interface FileService {

  FileResponseDto createFile(FileCreateDto createDto, String savedName, String url);

  FileResponseDto findById(Long id);

}
