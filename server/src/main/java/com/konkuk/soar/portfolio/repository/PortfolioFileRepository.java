package com.konkuk.soar.portfolio.repository;

import com.konkuk.soar.portfolio.domain.id.PortfolioAndFileId;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioFileRepository extends JpaRepository<PortfolioFile, PortfolioAndFileId> {

}
