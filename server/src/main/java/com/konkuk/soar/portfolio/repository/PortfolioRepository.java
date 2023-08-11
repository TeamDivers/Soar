package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

}
