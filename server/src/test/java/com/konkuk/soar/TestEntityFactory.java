package com.konkuk.soar;

import com.konkuk.soar.domain.common.File;
import com.konkuk.soar.domain.common.Tag;
import com.konkuk.soar.domain.member.Member;
import com.konkuk.soar.domain.portfolio.Portfolio;
import com.konkuk.soar.domain.portfolio.project.Project;
import com.konkuk.soar.domain.studyhistory.StudyHistory;

import java.time.LocalDateTime;

public class TestEntityFactory {

    static Portfolio portfolio(Member member) {
        return Portfolio.builder()
                .member(member)
                .isPublic(true)
                .category("ct")
                .title("t")
                .description("de")
                .build();
    }

    static Project project(Portfolio portfolio) {
        return Project.builder()
                .portfolio(portfolio)
                .title("title")
                .role("role")
                .description("desc")
                .category("ctg")
                .designBackground("bg")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .build();
    }

    static StudyHistory studyHistory(Member member) {
        return StudyHistory.builder()
                .member(member)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .content("con")
                .category("ct")
                .isPublic(true)
                .type("type")
                .build();
    }

    static File file() {
        return File.builder()
                .url("url")
                .type("type")
                .savedName("sn")
                .originalName("on")
                .build();
    }

    static Tag tag() {
        return Tag.builder()
                .name("name")
                .build();
    }

}
