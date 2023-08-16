package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.repository.TagRepository;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.portfolio.PortfolioTag;
import com.konkuk.soar.portfolio.repository.PortfolioTagRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.repository.StudyHistoryTagRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;
  private final StudyHistoryTagRepository studyHistoryTagRepository;
  private final PortfolioTagRepository portfolioTagRepository;

  @Transactional
  public Tag addTagToPortfolio(Portfolio portfolio, String tagName) {
    Tag tag = createTag(tagName);
    PortfolioTag portfolioTag = PortfolioTag.builder()
        .portfolio(portfolio)
        .tag(tag)
        .build();
    portfolioTagRepository.save(portfolioTag);
    return tag;
  }

  @Transactional
  public List<Tag> addAllTagToPortfolio(Portfolio portfolio, List<String> tagNameList) {
    List<Tag> res = new ArrayList<>();
    for (String tagName : tagNameList) {
      res.add(addTagToPortfolio(portfolio, tagName));
    }
    return res;
  }

  @Transactional
  public Tag addTagToStudyHistory(StudyHistory history, String tagName) {
    Tag tag = createTag(tagName);
    StudyHistoryTag historyTag = StudyHistoryTag.builder()
        .studyHistory(history)
        .tag(tag)
        .build();
    studyHistoryTagRepository.save(historyTag);
    return tag;
  }

  private Tag createTag(String tagName) {
    return tagRepository.findByName(tagName)
        .orElse(tagRepository.save(
            Tag.builder()
                .name(tagName)
                .build()
        ));
  }

  @Transactional
  public Optional<Tag> findTag(String name) {
    return tagRepository.findByName(name);
  }

}