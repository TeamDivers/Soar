package com.konkuk.soar.common.dto.file.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@Schema(description = "파일 저장 시 필요한 request body dto")
public class FileCreateDto {

  @Schema(description = "파일 타입")
  private String type;
  @Schema(description = "파일명")
  private String fileName;
  @Schema(description = "실제 파일")
  private MultipartFile file;
}
