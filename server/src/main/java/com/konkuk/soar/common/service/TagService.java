package com.konkuk.soar.common.service;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.repository.TagRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.repository.StudyHistoryTagRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;
  private final StudyHistoryTagRepository studyHistoryTagRepository;

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
    Tag tag = tagRepository.findByName(tagName)
        .orElse(tagRepository.save(
            Tag.builder()
                .name(tagName)
                .build()
        ));
    return tag;
  }

  @Transactional
  public Optional<Tag> findTag(String name) {
    return tagRepository.findByName(name);
  }

}
