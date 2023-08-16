package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
  List<Portfolio> findByMemberIdOrderByCreateAtAsc(Long memberId);
  List<Portfolio> findByMemberIdOrderByCreateAtDesc(Long memberId);

  List<Portfolio> findByMemberIdAndIsPublic(Long memberId, Boolean isPublic);

  List<Portfolio> findByMemberId(Long memberId);

  List<Portfolio> findAllByTitleContainingOrDescriptionContaining(String titleKeyword,
      String descriptionKeyword);
}