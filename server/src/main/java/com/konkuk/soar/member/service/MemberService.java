package com.konkuk.soar.member.service;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.dto.request.MemberUpdateRequestDto;
import com.konkuk.soar.member.dto.response.MemberResponseDto;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {

  void registerMember(Member member);
  Optional<Member> findMemberByEmail(String email);

  MemberResponseDto getMemberById(Long id);
  MemberResponseDto updateMember(MemberUpdateRequestDto updateMember);

  MemberResponseDto uploadProfile(Long id, MultipartFile multipartFile);

  Optional<Member> findById(Long id);

  List<MemberResponseDto> searchByKeyword(String keyword, int size);

}