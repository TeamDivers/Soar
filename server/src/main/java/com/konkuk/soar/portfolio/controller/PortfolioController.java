package com.konkuk.soar.portfolio.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateLargeDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@Tag(name = "Portfolio", description = "포트폴리오 관련 API Document")
public class PortfolioController {

  private final PortfolioService portfolioService;

  @Operation(summary = "포트폴리오 조회", description = "포트폴리오 id 기반 단일 조회.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class)))
  })
  @GetMapping("/{portfolioId}")
  public BaseResponse<PortfolioResponseDto> getPortfolio(@PathVariable Long portfolioId) {
    PortfolioResponseDto result = portfolioService.getPortfolioById(portfolioId);
    return BaseResponse.success(result);
  }

  @GetMapping("/rank/{portfolioId}")
  public int getScore(@PathVariable Long portfolioId) {
    return portfolioService.getRankByPortfolioScore(portfolioId);
  }

  @Operation(summary = "포트폴리오 리스트 조회", description = "회원이 작성한 포트폴리오 리스트를 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 리스트 조회 성공", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class)))
  })
  @GetMapping
  public BaseResponse<List<PortfolioResponseDto>> getPortfolioList(@RequestParam Long memberId,
      @RequestParam String option,
      @RequestParam(required = false, defaultValue = "5") Integer size) {
    OptionType optionType = OptionType.of(option);
    List<PortfolioResponseDto> result = portfolioService.getPortfolioListByMember(
        memberId, optionType, size);
    return BaseResponse.success(result);
  }

  @Operation(summary = "포트폴리오 생성", description = "포트폴리오를 작성합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "정상적으로 포트폴리오 작성 성공.", content = @Content(schema = @Schema(implementation = PortfolioOverviewDto.class)))
  })
  @PostMapping
  public BaseResponse<PortfolioOverviewDto> createPortfolio(
      @RequestBody PortfolioCreateLargeDto dto) {
    PortfolioOverviewDto result = portfolioService.createPortfolio(dto);
    return BaseResponse.success(result);
  }

  @Operation(summary = "포트폴리오 키워드로 검색", description = "포트폴리오 검색 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 성공", content = @Content(schema = @Schema(implementation = PortfolioOverviewDto.class)))
  })
  @GetMapping("/search")
  public BaseResponse<List<PortfolioOverviewDto>> updateMember(
      @RequestParam String keyword, @RequestParam Integer size) {
    List<PortfolioOverviewDto> result = portfolioService.searchByKeyword(keyword,
        size);
    return BaseResponse.success(result);
  }

  @Operation(summary = "포트폴리오 삭제", description = "포트폴리오와 해당 포트폴리오 내에 있는 프로젝트를 모두 삭제합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 삭제되었습니다.")
  })
  @DeleteMapping("/{portfolioId}")
  public BaseResponse<Void> deletePortfolio(@PathVariable Long portfolioId) {
    portfolioService.deletePortfolio(portfolioId);
    return BaseResponse.success(null);
  }

}