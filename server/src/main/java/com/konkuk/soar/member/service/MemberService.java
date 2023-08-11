package com.konkuk.soar.member.service;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public void registerMember(Member requestMember) {
    if (memberRepository.existsByEmail(requestMember.getEmail())) {
      return;
    }
    Member newMember = requestMember;
    newMember.setName("임시유저");
    memberRepository.save(newMember);
  }

  public Optional<Member> findMemberByEmail(String email) {
    return memberRepository.getByEmail(email);
  }
}
