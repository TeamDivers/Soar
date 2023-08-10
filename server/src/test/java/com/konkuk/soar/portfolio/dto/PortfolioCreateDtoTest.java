package com.konkuk.soar.portfolio.dto;

import static com.konkuk.soar.TestEntityFactory.member;
import static com.konkuk.soar.TestEntityFactory.portfolio;
import static org.junit.jupiter.api.Assertions.*;

import com.konkuk.soar.TestEntityFactory;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.Portfolio;
import com.konkuk.soar.portfolio.repository.PortfolioRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PortfolioCreateDtoTest {

    @Autowired
    PortfolioRepository portfolioRepository;

    @Test
    @Transactional
    void 빈_포트폴리오_생성() throws Exception {
        //given
        Member member = member();

        //when
        Portfolio portfolio = portfolio(member);
        portfolioRepository.save(portfolio);

        //then

    }
}