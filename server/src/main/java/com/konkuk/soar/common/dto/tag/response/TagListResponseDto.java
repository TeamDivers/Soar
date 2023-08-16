package com.konkuk.soar.common.dto.tag.response;

import com.konkuk.soar.common.domain.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagListResponseDto {

  private List<String> name;

  @Builder
  public TagListResponseDto(List<Tag> tagList) {
    name = new ArrayList<>();
    name = tagList.stream()
        .map(Tag::getName)
        .collect(Collectors.toList());
  }
}
