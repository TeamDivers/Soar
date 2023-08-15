package com.konkuk.soar.common.dto.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Schema(description = "파일 저장 시 필요한 request body dto")
public class FileCreateDto {

  @Schema(name = "파일 타입")
  private String type;
  @Schema(name = "파일명")
  private String fileName;
  @Schema(name = "실제 파일")
  private MultipartFile file;
}
