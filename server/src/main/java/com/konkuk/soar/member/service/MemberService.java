package com.konkuk.soar.member.service;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public Optional<Member> findById(Long id) {
    return memberRepository.findById(id);
  }
}
