package com.konkuk.soar.common.dto.file.response;

import com.konkuk.soar.common.domain.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileResponseDto {

  private Long fileId;
  private String type;
  private String fileName;
  private String url;

  @Builder
  public FileResponseDto(File file) {
    fileId = file.getId();
    type = file.getType();
    fileName = file.getOriginalName();
    url = file.getUrl();
  }
}
