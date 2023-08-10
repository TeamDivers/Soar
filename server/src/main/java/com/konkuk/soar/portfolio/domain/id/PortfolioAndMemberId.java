package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.Portfolio;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PortfolioAndMemberId implements Serializable {

    private Member member;
    private Portfolio portfolio;
}
