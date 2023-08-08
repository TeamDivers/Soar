package com.konkuk.soar.repository.member;

import com.konkuk.soar.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
