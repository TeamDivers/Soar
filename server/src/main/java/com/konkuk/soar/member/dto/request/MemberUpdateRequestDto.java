package com.konkuk.soar.member.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberUpdateRequestDto {

  private Long id;
  private String name;
  private String phoneNumber;
  private String education;
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
