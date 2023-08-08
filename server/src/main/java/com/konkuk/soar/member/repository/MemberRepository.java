package com.konkuk.soar.member.repository;

import com.konkuk.soar.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
