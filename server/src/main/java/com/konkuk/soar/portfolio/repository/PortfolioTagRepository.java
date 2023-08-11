package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.portfolio.PortfolioTag;
import com.konkuk.soar.portfolio.domain.id.PortfolioAndTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioTagRepository extends JpaRepository<PortfolioTag, PortfolioAndTagId> {

}
