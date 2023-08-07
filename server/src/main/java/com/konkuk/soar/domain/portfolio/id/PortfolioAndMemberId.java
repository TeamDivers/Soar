package com.konkuk.soar.domain.portfolio.id;

import com.konkuk.soar.domain.member.Member;
import com.konkuk.soar.domain.portfolio.Portfolio;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PortfolioAndMemberId implements Serializable {
    private Member member;
    private Portfolio portfolio;
}
