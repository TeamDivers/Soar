package com.konkuk.soar.portfolio.dto.project.request;

import static org.junit.jupiter.api.Assertions.*;
import static com.konkuk.soar.TestEntityFactory.*;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.common.repository.FileRepository;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectFileRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectRepository;
import com.konkuk.soar.portfolio.repository.project.ProjectStudyHistoryRepository;
import com.konkuk.soar.studyhistory.repository.StudyHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ProjectCreateDtoTest {

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
  public void 프로젝트_생성_성공_테스트_기본() throws Exception {
    //given
    Member member = member();
    memberRepository.save(member);

    Portfolio portfolio = portfolio(member);
    portfolioRepository.save(portfolio);

    Project project = project(portfolio);
    projectRepository.save(project);

    //when
    ProjectCreateDto dto = ProjectCreateDto.builder()
        .id(project.getId())
        .build();

    //then
    assertNotNull(dto.getId());
    assertEquals(dto.getId(), project.getId());
  }

  @Test
  public void 프로젝트_생성_실패_테스트_기본() throws Exception {
    //given
    Member member = member();
    Portfolio portfolio = portfolio(member);
    Project project = project(portfolio);

    //when
    ProjectCreateDto dto = ProjectCreateDto.builder()
        .id(project.getId())
        .build();

    //then
    assertNull(dto.getId());
  }



}