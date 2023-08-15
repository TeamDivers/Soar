package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import java.util.List;

public interface PortfolioService {

  PortfolioOverviewDto createPortfolio(PortfolioCreateDto dto);
  PortfolioResponseDto getPortfolioById(Long portfolioId);
  List<PortfolioResponseDto> getPortfolioListByMember(Long memberId, OptionType optionType,
      Integer size);

  List<PortfolioResponseDto> getPortfolioListByBookmark(Long memberId);
  List<PortfolioResponseDto> getPortfolioListByPopular();

}
