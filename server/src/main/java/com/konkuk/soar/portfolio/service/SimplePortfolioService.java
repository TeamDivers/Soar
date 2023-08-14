package com.konkuk.soar.portfolio.service;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioBookmark;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioReview;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioTag;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import com.konkuk.soar.portfolio.dto.portfolio.response.PortfolioResponseDto;
import com.konkuk.soar.portfolio.dto.project.response.ProjectResponseDto;
import com.konkuk.soar.portfolio.enums.OptionType;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import java.util.List;
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

  @Override
  @Transactional
  public PortfolioResponseDto getPortfolioById(Long portfolioId) {
    Portfolio portfolio = portfolioRepository.findById(portfolioId)
        .orElseThrow(() -> NotFoundException.PORTFOLIO_NOT_FOUND);

    return getResponseDto(portfolio);
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
      return res.stream().map(this::getResponseDto).collect(Collectors.toList());
    }

    throw new RuntimeException();
  }

  @Override
  public List<PortfolioResponseDto> getPortfolioListByBookmark(Long memberId) {
    return null;
  }

  @Override
  public List<PortfolioResponseDto> getPortfolioListByPopular() {
    return null;
  }

  protected PortfolioResponseDto getResponseDto(Portfolio portfolio) {
    Member member = portfolio.getMember();
    List<PortfolioBookmark> bookmarkList = portfolio.getBookmarkList();
    List<ProjectResponseDto> projectList = portfolio.getProjectList().stream().map(
        project -> ProjectResponseDto.builder().project(project).portfolio(portfolio).member(member)
            .fileList(project.getProjectFiles().stream().map(ProjectFile::getFile)
                .collect(Collectors.toList())).studyHistoryList(
                project.getStudyHistoryList().stream().map(ProjectStudyHistory::getStudyHistory)
                    .collect(Collectors.toList())).build()).collect(Collectors.toList());

    List<Tag> tagList = portfolio.getTagList().stream().map(PortfolioTag::getTag)
        .collect(Collectors.toList());

    List<PortfolioReview> reviewList = portfolio.getReviewList();

    return PortfolioResponseDto.builder().member(member).portfolio(portfolio).tagList(tagList)
        .reviewList(reviewList).projectList(projectList).bookmark(bookmarkList.size()).build();
  }
}
