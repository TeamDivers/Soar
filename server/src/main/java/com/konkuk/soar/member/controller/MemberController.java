package com.konkuk.soar.member.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.member.dto.request.MemberUpdateRequestDto;
import com.konkuk.soar.member.dto.response.MemberResponseDto;
import com.konkuk.soar.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/{id}")
  public BaseResponse<MemberResponseDto> getMember(@PathVariable Long id) {
    MemberResponseDto memberResponseDto = memberService.getMemberById(id);
    return BaseResponse.success(memberResponseDto);
  }

  @PutMapping("/edit")
  public BaseResponse<MemberResponseDto> updateMember(
      @RequestBody MemberUpdateRequestDto updateMember) {
    MemberResponseDto memberResponseDto = memberService.updateMember(updateMember);
    return BaseResponse.success(memberResponseDto);
  }

}
