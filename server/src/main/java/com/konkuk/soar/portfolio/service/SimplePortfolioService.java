package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.service.TagService;
import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.service.MemberService;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioBookmark;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioTag;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import com.konkuk.soar.portfolio.dto.portfolio.PortfolioScore;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateDto;
import com.konkuk.soar.portfolio.dto.portfolio.request.PortfolioCreateLargeDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioOverviewDto;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimplePortfolioService implements PortfolioService {

  private final PortfolioRepository portfolioRepository;

  private final ProjectService projectService;
  private final MemberService memberService;
  private final TagService tagService;

  @Override
  @Transactional
  public PortfolioOverviewDto createPortfolio(PortfolioCreateDto dto) {
    Member member = memberService.findById(dto.getMemberId())
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);

    Portfolio portfolio = Portfolio.builder()
        .title(dto.getTitle())
        .description(dto.getDescription())
        .category(dto.getCategory())
        .isPublic(dto.isPublic())
        .member(member)
        .build();

    portfolioRepository.save(portfolio);

    List<Tag> tags = tagService.addAllTagToPortfolio(portfolio, dto.getTags());
    Integer rank = getRankByPortfolioScore(portfolio);
    float score = getScore(portfolio);
    return getOverview(portfolio, rank, score);
  }

  @Override
  @Transactional
  public PortfolioOverviewDto createPortfolio(PortfolioCreateLargeDto dto) {
    PortfolioCreateDto portfolioCreateDto = dto.getPortfolio();
    List<ProjectCreateDto> projectsDto = dto.getProjects();

    PortfolioOverviewDto portfolio = createPortfolio(portfolioCreateDto);
    for (ProjectCreateDto projectCreateDto : projectsDto) {
      projectCreateDto.setPortfolioId(portfolio.getPortfolioId());
      projectService.createProject(projectCreateDto);
    }
    return portfolio;
  }

  @Override
  @Transactional
  public PortfolioResponseDto getPortfolioById(Long portfolioId) {
    Portfolio portfolio = portfolioRepository.findById(portfolioId)
        .orElseThrow(() -> NotFoundException.PORTFOLIO_NOT_FOUND);
    Integer rank = getRankByPortfolioScore(portfolio);
    float score = getScore(portfolio);
    return getResponseDto(portfolio, rank, score);
  }

  @Override
  public Optional<Portfolio> getPortfolioEntityById(Long portfolioId) {
    return portfolioRepository.findById(portfolioId);
  }

  @Override
  @Transactional
  public List<PortfolioResponseDto> getPortfolioListByMember(Long memberId, OptionType optionType,
      Integer size) {

    List<Portfolio> res;
    switch (optionType) {
      case NEWEST:
        res = portfolioRepository.findByMemberIdOrderByCreateAtDesc(memberId,
            Pageable.ofSize(size));
        break;
      case OLDEST:
        res = portfolioRepository.findByMemberIdOrderByCreateAtAsc(memberId, Pageable.ofSize(size));
        break;
      case RANK:
        res = null;
        break;
      case PUBLIC:
        res = portfolioRepository.findByMemberIdAndIsPublic(memberId, true, Pageable.ofSize(size));
        break;
      case PRIVATE:
        res = portfolioRepository.findByMemberIdAndIsPublic(memberId, false, Pageable.ofSize(size));
        break;
      default:
        res = portfolioRepository.findByMemberId(memberId, Pageable.ofSize(size));
        break;
    }

    if (res != null) {
      return res.stream()
          .map(pf -> getResponseDto(pf, getRankByPortfolioScore(pf), getScore(pf)))
          .collect(Collectors.toList());
    }

    throw new RuntimeException();
  }

  @Override
  @Transactional
  public Integer getRankByPortfolioScore(Long portfolioId) {
    Portfolio portfolio = portfolioRepository.findById(portfolioId)
        .orElseThrow(() -> NotFoundException.PORTFOLIO_NOT_FOUND);
    return this.getRankByPortfolioScore(portfolio);
  }

  /**
   * @param portfolio
   * @return 순위 산정방식 1. 현재 포트폴리오의 모든 리뷰의 평점 평균을 계산 2. 모든 포트폴리오의 평균을 계산한 뒤, 해당 포트폴리오의 등수 계산 추가적인 생각되는
   * 방식 1. 같은 태그를 지닌 얘들을 대상으로 위의 프로세스 진행
   */
  @Override
  @Transactional
  public Integer getRankByPortfolioScore(Portfolio portfolio) {
    Long portfolioId = portfolio.getId();
    List<PortfolioScore> scoreList = portfolioRepository.findAll().stream().map(pf -> {
      float score = getScore(pf);
      return PortfolioScore.builder()
          .portfolioId(pf.getId())
          .score(score)
          .build();
    }).sorted().toList();
    int idx = scoreList.indexOf(PortfolioScore.builder().portfolioId(portfolioId).build());

    if (idx == -1) {
      throw NotFoundException.PORTFOLIO_NOT_FOUND;
    }
    return idx + 1;
  }

  private static float getScore(Portfolio portfolio) {
    float sum = 0;
    List<PortfolioReview> reviewList = portfolio.getReviewList();
    for (PortfolioReview pr : reviewList) {
      float score =
          (pr.getExpertiseScore() + pr.getPerfectionScore() + pr.getDifferenceScore()) / 3;
      sum += score;
    }
    int reviewNumber = reviewList.size();
    if (reviewNumber != 0) {
      sum /= reviewNumber;
    }
    return sum;
  }

  @Override
  public List<PortfolioResponseDto> getPortfolioListByBookmark(Long memberId) {
    return null;
  }

  @Override
  public List<PortfolioResponseDto> getPortfolioListByPopular() {
    return null;
  }

  protected PortfolioOverviewDto getOverview(Portfolio portfolio, Integer rank, Float score) {
    List<PortfolioBookmark> bookmarkList = portfolio.getBookmarkList();
    List<Tag> tagList = portfolio.getTagList().stream()
        .map(PortfolioTag::getTag)
        .toList();

    return PortfolioOverviewDto.builder()
        .portfolio(portfolio)
        .bookmark(bookmarkList.size())
        .member(portfolio.getMember())
        .rank(rank)
        .score(score)
        .tagList(tagList)
        .build();
  }

  protected PortfolioResponseDto getResponseDto(Portfolio portfolio, Integer rank, Float score) {
    Member member = portfolio.getMember();
    List<PortfolioBookmark> bookmarkList = portfolio.getBookmarkList();
    List<ProjectResponseDto> projectList = portfolio.getProjectList().stream()
        .map(project -> ProjectResponseDto.builder()
            .project(project)
            .portfolio(portfolio)
            .member(member)
            .fileList(project.getProjectFiles().stream()
                .map(ProjectFile::getFile)
                .collect(Collectors.toList()))
            .studyHistoryList(project.getStudyHistoryList().stream()
                .map(ProjectStudyHistory::getStudyHistory)
                .collect(Collectors.toList()))
            .build())
        .collect(Collectors.toList());

    List<Tag> tagList = portfolio.getTagList().stream()
        .map(PortfolioTag::getTag)
        .collect(Collectors.toList());

    List<PortfolioReview> reviewList = portfolio.getReviewList();
    return PortfolioResponseDto.builder()
        .member(member)
        .portfolio(portfolio)
        .tagList(tagList)
        .reviewList(reviewList)
        .rank(rank)
        .score(score)
        .projectList(projectList)
        .bookmark(bookmarkList.size())
        .build();
  }
}