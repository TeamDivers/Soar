package com.konkuk.soar.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원 조회 시 response body dto")
public class MemberResponseDto {

  @Schema(description = "회원 아이디")
  private Long id;
  @Schema(description = "회원 이메일")
  private String email;
  @Schema(description = "회원 이름")
  private String name;
  @Schema(description = "회원 전화번호", defaultValue = "")
  private String phoneNumber;
  @Schema(description = "회원 학력", defaultValue = "")
  private String education;
  @Schema(description = "회원 경력", defaultValue = "")
  private String career;

  @Builder
  public MemberResponseDto(Long id, String email, String name, String phoneNumber, String education,
      String career) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.education = education;
    this.career = career;
  }
}
