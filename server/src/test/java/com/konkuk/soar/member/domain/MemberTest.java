package com.konkuk.soar.member.domain;

import com.konkuk.soar.member.repository.MemberRepository;
import com.konkuk.soar.portfolio.domain.Portfolio;
import com.konkuk.soar.portfolio.domain.PortfolioBookmark;
import com.konkuk.soar.portfolio.repository.PortfolioBookmarkRepository;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static com.konkuk.soar.TestEntityFactory.*;

@SpringBootTest
@ActiveProfiles("test")
class MemberTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PortfolioRepository portfolioRepository;
    @Autowired
    PortfolioBookmarkRepository bookmarkRepository;

    @BeforeEach
    void beforeEach() {
        memberRepository.deleteAll();
        portfolioRepository.deleteAll();
        bookmarkRepository.deleteAll();
    }

    @Test
    @Transactional
    void 멤버_삭제시_포트폴리오_삭제_테스트() {
        //given
        Member member = member();
        memberRepository.save(member);

        Portfolio portfolio = portfolio(member);
        portfolioRepository.save(portfolio);

        //when
        memberRepository.delete(member);

        //then
    }

    @Test
    @Transactional
    void 멤버_삭제시_북마크_삭제_테스트() {
        //given
        Member member = member();
        memberRepository.save(member);

        Portfolio portfolio = portfolio(member);
        portfolioRepository.save(portfolio);

        PortfolioBookmark bookmark = portfolioBookmark(member, portfolio);
        bookmarkRepository.save(bookmark);

        //when
        memberRepository.deleteById(member.getId());
    }
}