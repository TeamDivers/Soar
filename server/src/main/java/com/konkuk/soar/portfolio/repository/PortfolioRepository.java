package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  List<Portfolio> findByMemberIdOrderByCreateAtAsc(Long memberId, Pageable pageable);
  List<Portfolio> findByMemberIdOrderByCreateAtDesc(Long memberId, Pageable pageable);

  List<Portfolio> findByMemberIdAndIsPublic(Long memberId, Boolean isPublic, Pageable pageable);

  List<Portfolio> findByMemberId(Long memberId, Pageable pageable);

  List<Portfolio> findAllByTitleContainingOrDescriptionContaining(String titleKeyword,
      String descriptionKeyword, Pageable pageable);
}