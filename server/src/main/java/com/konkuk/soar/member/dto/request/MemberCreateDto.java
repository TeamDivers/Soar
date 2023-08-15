package com.konkuk.soar.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateDto {

  private String name;
  private String email;
}
