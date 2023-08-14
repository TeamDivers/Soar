package com.konkuk.soar.portfolio.dto.project.response;

import static com.konkuk.soar.TestEntityFactory.file;
import static com.konkuk.soar.TestEntityFactory.member;
import static com.konkuk.soar.TestEntityFactory.portfolio;
import static com.konkuk.soar.TestEntityFactory.project;
import static com.konkuk.soar.TestEntityFactory.studyHistory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.repository.FileRepository;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.domain.project.ProjectFile;
import com.konkuk.soar.portfolio.domain.project.ProjectStudyHistory;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectFileRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectStudyHistoryRepository;
import com.konkuk.soar.studyhistory.domain.StudyHistory;
import com.konkuk.soar.studyhistory.repository.StudyHistoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProjectResponseDtoTest {

  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  PortfolioRepository portfolioRepository;
  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  FileRepository fileRepository;
  @Autowired
  ProjectFileRepository projectFileRepository;
  @Autowired
  StudyHistoryRepository studyHistoryRepository;
  @Autowired
  ProjectStudyHistoryRepository projectStudyHistoryRepository;

  @Test
  @Transactional
  void test() {
    //given
    Member member = member();
    memberRepository.save(member);

    Portfolio portfolio = portfolio(member);
    portfolioRepository.save(portfolio);

    Project project = project(portfolio);
    projectRepository.save(project);

    File file1 = file();
    File file2 = file();
    List<File> fileList = new ArrayList<>();
    fileList.add(file1);
    fileList.add(file2);

    fileRepository.saveAll(fileList);

    ProjectFile pff1 = ProjectFile.builder().file(file1).project(project).build();
    ProjectFile pff2 = ProjectFile.builder().file(file2).project(project).build();

    projectFileRepository.save(pff1);
    projectFileRepository.save(pff2);

    List<StudyHistory> historyList = new ArrayList<>();
    StudyHistory history1 = studyHistory(member);
    StudyHistory history2 = studyHistory(member);
    historyList.add(history1);
    historyList.add(history2);

    studyHistoryRepository.saveAll(historyList);

    ProjectStudyHistory ps1 = ProjectStudyHistory.builder().studyHistory(history1)
        .project(project).build();
    ProjectStudyHistory ps2 = ProjectStudyHistory.builder().studyHistory(history2)
        .project(project).build();
    projectStudyHistoryRepository.save(ps1);
    projectStudyHistoryRepository.save(ps2);

    //when
    Project found = projectRepository.findById(project.getId())
        .orElseThrow(() -> new RuntimeException("not found"));

    Portfolio foundPortfolio = found.getPortfolio();
    Member foundMember = foundPortfolio.getMember();
    List<File> foundFiles = found.getProjectFiles().stream().map(ProjectFile::getFile)
        .collect(Collectors.toList());
    List<StudyHistory> foundHistories = found.getStudyHistoryList().stream()
        .map(ProjectStudyHistory::getStudyHistory)
        .collect(Collectors.toList());

    ProjectResponseDto dto = ProjectResponseDto.builder()
        .member(foundMember)
        .portfolio(foundPortfolio)
        .fileList(foundFiles)
        .studyHistoryList(foundHistories)
        .project(found)
        .build();
    String res = null;
    try {
      res = objectMapper.writeValueAsString(dto);
      System.out.println(res);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

  }
}