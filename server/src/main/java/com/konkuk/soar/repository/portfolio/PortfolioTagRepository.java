package com.konkuk.soar.repository.portfolio;

import com.konkuk.soar.domain.portfolio.PortfolioTag;
import com.konkuk.soar.domain.portfolio.id.PortfolioAndTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioTagRepository extends JpaRepository<PortfolioTag, PortfolioAndTagId> {

}
