package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PortfolioAndTagId implements Serializable {

  private Portfolio portfolio;
  private Tag tag;
}
