package com.konkuk.soar.common.dto.file.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class FileCreateDto {

  private String type;
  private String fileName;
  private MultipartFile file;
}
