package com.konkuk.soar.portfolio.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.service.PortfolioService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

  private final PortfolioService portfolioService;

  @GetMapping("/{portfolioId}")
  public BaseResponse<PortfolioResponseDto> getPortfolio(@PathVariable Long portfolioId) {
    PortfolioResponseDto result = portfolioService.getPortfolioById(portfolioId);
    return BaseResponse.success(result);
  }

  @GetMapping
  public BaseResponse<List<PortfolioResponseDto>> getPortfolioList(@RequestParam Long memberId,
      @RequestParam String option,
      @RequestParam(required = false, defaultValue = "5") Integer size) {
    OptionType optionType = OptionType.of(option);
    List<PortfolioResponseDto> result = portfolioService.getPortfolioListByMember(
        memberId, optionType, size);
    return BaseResponse.success(result);
  }

  @PostMapping
  public BaseResponse<PortfolioOverviewDto> createPortfolio(@RequestBody PortfolioCreateDto dto) {
    PortfolioOverviewDto result = portfolioService.createPortfolio(dto);
    return BaseResponse.success(result);
  }
}