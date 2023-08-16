package com.konkuk.soar.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.member.dto.request.MemberUpdateRequestDto;
import com.konkuk.soar.member.dto.response.MemberResponseDto;
import com.konkuk.soar.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
@Tag(name = "Member", description = "회원 관련 API Document")
public class MemberController {

  private final MemberService memberService;

  @Operation(summary = "회원 조회", description = "단일 회원 조회를 위한 API입니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 조회 성공", content = @Content(schema = @Schema(implementation = MemberResponseDto.class))),
  })
  @GetMapping("/{id}")
  public BaseResponse<MemberResponseDto> getMember(@PathVariable Long id) {
    MemberResponseDto memberResponseDto = memberService.getMemberById(id);
    return BaseResponse.success(memberResponseDto);
  }

  @Operation(summary = "회원 정보 수정", description = "회원 정보 수정을 위한 API입니다.(이메일 수정 불가)")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 수정 성공", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
  })
  @PutMapping("/edit")
  public BaseResponse<MemberResponseDto> updateMember(
      @RequestBody MemberUpdateRequestDto updateMember) {
    MemberResponseDto memberResponseDto = memberService.updateMember(updateMember);
    return BaseResponse.success(memberResponseDto);
  }

  @Operation(summary = "회원 이름으로 검색", description = "회원 이름 검색 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 수정 성공", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
  })
  @GetMapping("/search")
  public BaseResponse<List<MemberResponseDto>> updateMember(
      @RequestParam String keyword, @RequestParam Integer size) {
    List<MemberResponseDto> memberResponseDtos = memberService.searchByKeyword(keyword,size);
    return BaseResponse.success(memberResponseDtos);
  }

  @Operation(summary = "프로필 사진 변경", description = "프로필 사진을 변경하는 api")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 수정 성공", content = @Content(schema = @Schema(implementation = MemberResponseDto.class)))
  })
  @PostMapping("/thumbnail")
  public BaseResponse<MemberResponseDto> updateThumbnail(
      @RequestParam("member") String memberId, @RequestParam(value = "file", required=false) MultipartFile file) {
    MemberResponseDto memberResponseDto = memberService.uploadProfile(Long.valueOf(memberId), file);
    return BaseResponse.success(memberResponseDto);
  }
}