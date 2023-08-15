package com.konkuk.soar.member.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponseDto {

  private Long id;
  private String email;
  private String name;
  private String phoneNumber;
  private String education;
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
