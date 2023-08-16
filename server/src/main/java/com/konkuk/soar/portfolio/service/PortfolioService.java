package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateLargeDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public interface PortfolioService {

  PortfolioOverviewDto createPortfolio(PortfolioCreateDto dto);

  PortfolioOverviewDto createPortfolio(PortfolioCreateLargeDto dto);
  PortfolioResponseDto getPortfolioById(Long portfolioId);

  Optional<Portfolio> getPortfolioEntityById(Long portfolioId);
  List<PortfolioResponseDto> getPortfolioListByMember(Long memberId, OptionType optionType,
      Integer size);

  Integer getRankByPortfolioScore(Long portfolioId);

  @Transactional
  Integer getRankByPortfolioScore(Portfolio portfolio);

  List<PortfolioResponseDto> getPortfolioListByBookmark(Long memberId);
  List<PortfolioResponseDto> getPortfolioListByPopular();

  List<PortfolioOverviewDto> searchByKeyword(String keyword, int size);

  void deletePortfolio(Long portfolioId);
}