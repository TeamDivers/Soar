package com.konkuk.soar.common.dto.file.response;

import com.konkuk.soar.common.domain.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "파일 조회 시 response body dto")
public class FileResponseDto {

  @Schema(name = "파일 id")
  private Long fileId;
  @Schema(name = "파일 타입")
  private String type;
  @Schema(name = "파일명")
  private String fileName;
  @Schema(name = "파일의 url")
  private String url;

  @Builder
  public FileResponseDto(File file) {
    fileId = file.getId();
    type = file.getType();
    fileName = file.getOriginalName();
    url = file.getUrl();
  }
}
