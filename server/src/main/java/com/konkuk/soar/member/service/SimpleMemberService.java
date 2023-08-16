package com.konkuk.soar.member.service;

import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.dto.request.MemberUpdateRequestDto;
import com.konkuk.soar.member.dto.response.MemberResponseDto;
import com.konkuk.soar.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SimpleMemberService implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  @Transactional
  public void registerMember(Member member) {
    if (memberRepository.existsByEmail(member.getEmail())) {
      return;
    }

    member.setName("임시유저");
    memberRepository.save(member);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Member> findMemberByEmail(String email) {
    return memberRepository.getByEmail(email);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberResponseDto getMemberById(Long id) {
    Member member = memberRepository.findById(id)
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);

    return MemberResponseDto.builder()
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
        .education(member.getEducation())
        .career(member.getCareer())
        .build();
  }

  @Override
  @Transactional
  public MemberResponseDto updateMember(MemberUpdateRequestDto updateMember) {
    Member member = memberRepository.findById(updateMember.getId())
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);
    member.setName(updateMember.getName());
    member.setCareer(updateMember.getCareer());
    member.setEducation(updateMember.getEducation());
    member.setPhoneNumber(updateMember.getPhoneNumber());

    return MemberResponseDto.builder()
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
        .education(member.getEducation())
        .career(member.getCareer())
        .build();
  }

  @Override
  public Optional<Member> findById(Long id) {
    return memberRepository.findById(id);
  }
}