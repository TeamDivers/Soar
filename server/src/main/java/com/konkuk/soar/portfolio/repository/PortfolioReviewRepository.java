package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.PortfolioReview;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioReviewRepository extends JpaRepository<PortfolioReview, PortfolioAndMemberId> {

}
