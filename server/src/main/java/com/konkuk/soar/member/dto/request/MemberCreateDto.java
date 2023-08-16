package com.konkuk.soar.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "회원가입 시 필요한 request body dto")
public class MemberCreateDto {

  private String name;
  private String email;
}