package com.konkuk.soar.common.dto.tag.response;

import com.konkuk.soar.common.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "태그리스트를 반환하는 response body dto")
public class TagListResponseDto {

  @Schema(name = "태그명 리스트")
  private List<String> name;

  @Builder
  public TagListResponseDto(List<Tag> tagList) {
    name = new ArrayList<>();
    name = tagList.stream()
        .map(Tag::getName)
        .collect(Collectors.toList());
  }
}
