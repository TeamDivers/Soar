package com.konkuk.soar.studyhistory.dto.response;

import static com.konkuk.soar.TestEntityFactory.file;
import static com.konkuk.soar.TestEntityFactory.member;
import static com.konkuk.soar.TestEntityFactory.studyHistory;
import static com.konkuk.soar.TestEntityFactory.tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class StudyHistoryCreateDtoTest {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  StudyHistoryRepository studyHistoryRepository;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  FileRepository fileRepository;
  @Autowired
  TagRepository tagRepository;
  @Autowired
  StudyHistoryTagRepository studyHistoryTagRepository;
  @Autowired
  StudyHistoryFileRepository studyHistoryFileRepository;


  @Test
  @Transactional
  public void 학습기록_생성_성공_테스트_기본() throws Exception {
    //given
    ClassBundle classBundle = createPortfolioWithSave();
    StudyHistory studyHistory = classBundle.studyHistory;

    //when
    StudyHistoryCreateResponseDto dto = StudyHistoryCreateResponseDto.builder()
        .id(studyHistory.getId())
        .build();

    //then
    assertNotNull(dto.getId());
    assertEquals(dto.getId(), studyHistory.getId());
  }

  @Test
  @Transactional
  public void 학습기록_생성_실패_테스트_기본() throws Exception {
    //given
    ClassBundle classBundle = createPortfolio();
    StudyHistory studyHistory = classBundle.studyHistory;

    //when
    StudyHistoryCreateResponseDto dto = StudyHistoryCreateResponseDto.builder()
        .id(studyHistory.getId())
        .build();

    //then
    assertNull(dto.getId());
  }

  @Transactional
  ClassBundle createPortfolioWithSave() {
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

  @Transactional
  ClassBundle createPortfolio() {
    Member member = member();
    memberRepository.save(member);

    StudyHistory studyHistory = studyHistory(member);

    List<File> fileList = new ArrayList<>();
    File timelapseFile = file();
    File file1 = file();
    File file2 = file();

    fileList.add(file1);
    fileList.add(file2);

    return new ClassBundle(member, studyHistory, fileList, timelapseFile, null);
  }

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

}