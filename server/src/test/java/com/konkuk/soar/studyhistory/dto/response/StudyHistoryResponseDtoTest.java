package com.konkuk.soar.studyhistory.dto.response;

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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.konkuk.soar.TestEntityFactory.*;

@SpringBootTest
@ActiveProfiles("test")
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
        List<Tag> tagList;

        public ClassBundle(Member member, StudyHistory studyHistory, List<File> fileList, File timelapse, List<Tag> tagList) {
            this.member = member;
            this.studyHistory = studyHistory;
            this.fileList = fileList;
            this.timelapse = timelapse;
            this.tagList = tagList;
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

        List<Tag> tagList = new ArrayList<>();
        Tag tag1 = tag();
        Tag tag2 = tag();

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        tagList.add(tag1);
        tagList.add(tag2);

        StudyHistoryTag shTag1 = StudyHistoryTag.builder()
                .studyHistory(studyHistory)
                .tag(tag1)
                .build();
        StudyHistoryTag shTag2 = StudyHistoryTag.builder()
                .studyHistory(studyHistory)
                .tag(tag2)
                .build();

        studyHistoryTagRepository.save(shTag1);
        studyHistoryTagRepository.save(shTag2);

        return new ClassBundle(member, studyHistory, fileList, timelapseFile, tagList);
    }

    @Test
    @Transactional
    void 학습기록_DTO_테스트() {
        ClassBundle bundle = createPortfolio();
        StudyHistoryResponseDto dto = StudyHistoryResponseDto.builder()
                .history(bundle.studyHistory)
                .member(bundle.member)
                .timelapseFile(bundle.timelapse)
                .fileList(bundle.fileList)
                .tagList(bundle.tagList)
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
        List<StudyHistoryMetaResponseDto> dtoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClassBundle bundle = createPortfolio();
            StudyHistoryMetaResponseDto dto = StudyHistoryMetaResponseDto.builder()
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