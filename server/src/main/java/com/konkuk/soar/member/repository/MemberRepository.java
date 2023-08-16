package com.konkuk.soar.member.repository;

import com.konkuk.soar.member.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByEmail(String email);

  Optional<Member> getByEmail(String email);

  List<Member> findAllByNameContains(String keyword, Pageable pageable);
}