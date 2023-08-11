package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PortfolioAndMemberId implements Serializable {

  private Member member;
  private Portfolio portfolio;
}
