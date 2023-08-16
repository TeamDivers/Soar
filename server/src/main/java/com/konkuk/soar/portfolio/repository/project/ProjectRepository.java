package com.konkuk.soar.portfolio.repository.project;

import com.konkuk.soar.portfolio.domain.project.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findByPortfolioId(Long portfolioId);

  void deleteProjectsByPortfolioId(Long portfolioId);
}