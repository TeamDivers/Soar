package com.konkuk.soar.portfolio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioReviewCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectOverviewDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.service.PortfolioService;
import com.konkuk.soar.portfolio.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/portfolios")
@Tag(name = "Portfolio", description = "포트폴리오 관련 API Document")
@CrossOrigin(originPatterns = "*")
public class PortfolioController {

  private final PortfolioService portfolioService;
  private final ProjectService projectService;
  private final ObjectMapper objectMapper;

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
  public BaseResponse<List<PortfolioResponseDto>> getPortfolioList(
      @RequestParam(required = false) Long memberId,
      @RequestParam(required = false) String option,
      @RequestParam(required = false, defaultValue = "5") Integer size) {
    List<PortfolioResponseDto> result;
    if (memberId != null) {
      OptionType optionType = OptionType.of(option);
      result = portfolioService.getPortfolioListByMember(
          memberId, optionType);
    } else if(option == null){
      result = portfolioService.getPortfolioList();
    } else {
      result = portfolioService.getPortfolioRank();
    }
    return BaseResponse.success(result);
  }

  @Operation(summary = "포트폴리오 별점 매기기", description = "포트폴리오를 작성합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 포트폴리오 별점 작성 성공.", content = @Content(schema = @Schema(implementation = PortfolioOverviewDto.class)))
  })
  @PostMapping("/rate")
  public BaseResponse<PortfolioResponseDto> ratePortfolio(@RequestBody PortfolioReviewCreateDto dto) {
    PortfolioResponseDto result = portfolioService.ratePortfolio(dto);
    return BaseResponse.success(result);
  }

  @Operation(summary = "포트폴리오 생성", description = "포트폴리오를 작성합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "정상적으로 포트폴리오 작성 성공.", content = @Content(schema = @Schema(implementation = PortfolioOverviewDto.class)))
  })
  @PostMapping
  public BaseResponse<PortfolioOverviewDto> createPortfolio(
      @RequestParam String portfolio,
      @RequestParam String projectList,
      @RequestPart(required = false) List<MultipartFile> files,
      @RequestPart(required = false) MultipartFile thumbnail,
      @RequestParam String fileNumbers) {
    try {
      objectMapper.readValue(portfolio, PortfolioCreateDto.class);
      PortfolioCreateDto createDto = objectMapper.readValue(portfolio,
          PortfolioCreateDto.class);
      ProjectCreateDto[] projectCreateDtoList = objectMapper.readValue(projectList,
          ProjectCreateDto[].class);
      Integer[] fileLengthList = objectMapper.readValue(fileNumbers, Integer[].class);

      PortfolioOverviewDto portfolioResult = portfolioService.createPortfolio(createDto, thumbnail);
      Long portfolioId = portfolioResult.getPortfolioId();
      int off = 0;
      for (int i = 0; i < projectCreateDtoList.length; i++) {
        ProjectCreateDto currDto = projectCreateDtoList[i];
        currDto.setPortfolioId(portfolioId);
        Integer currFileLen = fileLengthList[i];
        ProjectOverviewDto projectResult = projectService.createProject(currDto, files.subList(off,
            off + currFileLen));
        off += currFileLen;
      }
      return BaseResponse.success(portfolioResult);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Operation(summary = "포트폴리오 키워드로 검색", description = "포트폴리오 검색 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "정상적으로 성공", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class)))
  })
  @GetMapping("/search")
  public BaseResponse<List<PortfolioResponseDto>> updateMember(
      @RequestParam String keyword, @RequestParam Integer size) {
    List<PortfolioResponseDto> result = portfolioService.searchByKeyword(keyword);
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