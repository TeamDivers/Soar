package com.konkuk.soar.studyhistory.dto.response;

import static com.konkuk.soar.TestEntityFactory.file;
import static com.konkuk.soar.TestEntityFactory.member;
import static com.konkuk.soar.TestEntityFactory.studyHistory;
import static com.konkuk.soar.TestEntityFactory.tag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.common.repository.FileRepository;
import com.konkuk.soar.common.repository.TagRepository;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.domain.StudyHistoryFile;
import com.konkuk.soar.studyhistory.domain.StudyHistoryTag;
import com.konkuk.soar.studyhistory.repository.StudyHistoryFileRepository;
import com.konkuk.soar.studyhistory.repository.StudyHistoryRepository;
import com.konkuk.soar.studyhistory.repository.StudyHistoryTagRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class StudyHistoryResponseDtoTest {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  StudyHistoryRepository studyHistoryRepository;

  @Autowired
  FileRepository fileRepository;

  @Autowired
  StudyHistoryFileRepository studyHistoryFileRepository;

  @Autowired
  TagRepository tagRepository;

  @Autowired
  StudyHistoryTagRepository studyHistoryTagRepository;


  static class ClassBundle {

    Member member;
    StudyHistory studyHistory;
    List<File> fileList;
    File timelapse;
    StudyHistoryTag tag;

    public ClassBundle(Member member, StudyHistory studyHistory, List<File> fileList,
        File timelapse, StudyHistoryTag tag) {
      this.member = member;
      this.studyHistory = studyHistory;
      this.fileList = fileList;
      this.timelapse = timelapse;
      this.tag = tag;
    }
  }

  @Transactional
  ClassBundle createPortfolio() {
    Member member = member();
    memberRepository.save(member);

    StudyHistory studyHistory = studyHistory(member);
    studyHistoryRepository.save(studyHistory);

    List<File> fileList = new ArrayList<>();
    File timelapseFile = file();
    File file1 = file();
    File file2 = file();
    fileRepository.save(timelapseFile);
    fileRepository.save(file1);
    fileRepository.save(file2);

    fileList.add(file1);
    fileList.add(file2);

    StudyHistoryFile studyHistoryFile1 = StudyHistoryFile.builder()
        .studyHistory(studyHistory)
        .file(timelapseFile)
        .build();

    StudyHistoryFile studyHistoryFile2 = StudyHistoryFile.builder()
        .studyHistory(studyHistory)
        .file(file1)
        .build();

    StudyHistoryFile studyHistoryFile3 = StudyHistoryFile.builder()
        .studyHistory(studyHistory)
        .file(file2)
        .build();
    studyHistoryFileRepository.save(studyHistoryFile1);
    studyHistoryFileRepository.save(studyHistoryFile2);
    studyHistoryFileRepository.save(studyHistoryFile3);

    Tag tag = tag();
    tagRepository.save(tag);

    StudyHistoryTag shTag = StudyHistoryTag.builder()
        .studyHistory(studyHistory)
        .tag(tag)
        .build();
    studyHistoryTagRepository.save(shTag);

    return new ClassBundle(member, studyHistory, fileList, timelapseFile, shTag);
  }

  @Test
  @Transactional
  void 학습기록_DTO_테스트() {
    ClassBundle bundle = createPortfolio();
    StudyHistoryResponseDto dto = StudyHistoryResponseDto.builder()
        .history(bundle.studyHistory)
        .member(bundle.member)
        .tag(bundle.tag.getTag())
        .timelapseFile(bundle.timelapse)
        .fileList(bundle.fileList)
        .build();
    try {
      String res = objectMapper.writeValueAsString(dto);
      System.out.println(res);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @Transactional
  void 학습기록_리스트_테스트() {
    List<StudyHistoryOverviewDto> dtoList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      ClassBundle bundle = createPortfolio();
      StudyHistoryOverviewDto dto = StudyHistoryOverviewDto.builder()
          .history(bundle.studyHistory)
          .member(bundle.member)
          .build();
      dtoList.add(dto);
    }
    try {
      String res = objectMapper.writeValueAsString(dtoList);
      System.out.println(res);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}