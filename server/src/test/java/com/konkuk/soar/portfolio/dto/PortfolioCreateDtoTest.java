package com.konkuk.soar.portfolio.dto;

import static com.konkuk.soar.TestEntityFactory.member;
import static com.konkuk.soar.TestEntityFactory.portfolio;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.soar.TestEntityFactory;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.repository.MemberRepository;
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

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Transactional
    void 포트폴리오_DTO_테스트() throws Exception {
        //given
        Member member = member();
        memberRepository.save(member);
        Portfolio portfolio = portfolio(member);

        //when

        PortfolioCreateDto portfolioCreateDto = PortfolioCreateDto.builder()
                .title(portfolio.getTitle())
                .description(portfolio.getDescription())
                .category(portfolio.getCategory())
                .isPublic(portfolio.isPublic())
                .member(portfolio.getMember())
                .build();

        //then
        try {
            String result = objectMapper.writeValueAsString(portfolioCreateDto);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}