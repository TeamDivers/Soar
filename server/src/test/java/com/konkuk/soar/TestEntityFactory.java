package com.konkuk.soar;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.Portfolio;
import com.konkuk.soar.portfolio.domain.PortfolioBookmark;
import com.konkuk.soar.portfolio.domain.PortfolioReview;
import com.konkuk.soar.portfolio.domain.PortfolioTag;
import com.konkuk.soar.portfolio.domain.project.Project;
import com.konkuk.soar.studyhistory.domain.StudyHistory;

import java.time.LocalDateTime;

public class TestEntityFactory {

    public static Member member() {
        return Member.builder()
                .name("member")
                .build();
    }

    public static Portfolio portfolio(Member member) {
        return Portfolio.builder()
                .member(member)
                .isPublic(true)
                .category("ct")
                .title("t")
                .description("de")
                .build();
    }

    public static PortfolioBookmark portfolioBookmark(Member member, Portfolio portfolio) {
        return PortfolioBookmark.builder()
                .member(member)
                .portfolio(portfolio)
                .build();
    }

    public static PortfolioReview portfolioReview(Member member,Portfolio portfolio) {
        return PortfolioReview.builder()
                .comment("comment")
                .differenceScore(4.0F)
                .expertiseScore(4.0F)
                .perfectionScore(4.0F)
                .member(member)
                .portfolio(portfolio)
                .build();
    }

    public static PortfolioTag portfolioTag(Tag tag, Portfolio portfolio) {
        return PortfolioTag.builder()
                .tag(tag)
                .portfolio(portfolio)
                .build();
    }

    public static Project project(Portfolio portfolio) {
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

    public static StudyHistory studyHistory(Member member) {
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

    public static File file() {
        return File.builder()
                .url("url")
                .type("type")
                .savedName("sn")
                .originalName("on")
                .build();
    }

    public static Tag tag() {
        return Tag.builder()
                .name("name")
                .build();
    }

}
