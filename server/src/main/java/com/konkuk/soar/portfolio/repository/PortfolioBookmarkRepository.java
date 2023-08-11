package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioBookmarkRepository extends
    JpaRepository<PortfolioBookmark, PortfolioAndMemberId> {

  @Query("select count(i) > 0 from PortfolioBookmark i where i.member.id=:memberId and i.portfolio.id=:portfolioId")
  boolean existsById(@Param("memberId") Long memberId, @Param("portfolioId") Long portfolioId);
}
