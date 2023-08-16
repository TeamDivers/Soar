package com.konkuk.soar.common.dto.tag.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "태그 생성 시 request body dto")
public class TagCreateDto {

  @Schema(description = "태그명")
  private String name;
}