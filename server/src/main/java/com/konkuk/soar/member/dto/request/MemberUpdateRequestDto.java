package com.konkuk.soar.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "회원 정보 수정 시 request body dto")
public class MemberUpdateRequestDto {

  @Schema(description = "회원 id")
  private Long id;
  @Schema(description = "회원 이름")
  private String name;
  @Schema(description = "회원 전화번호")
  private String phoneNumber;
  @Schema(description = "회원 학력")
  private String education;
  @Schema(description = "회원 경력")
  private String career;

  @Builder
  public MemberUpdateRequestDto(Long id, String name, String phoneNumber, String education,
      String career) {
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.education = education;
    this.career = career;
  }
}
