package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioReviewRepository extends
    JpaRepository<PortfolioReview, PortfolioAndMemberId> {

  List<PortfolioReview> findAllByPortfolioId(Long portfolioId);
}