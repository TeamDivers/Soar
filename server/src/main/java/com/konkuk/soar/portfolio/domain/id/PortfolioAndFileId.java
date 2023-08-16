package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PortfolioAndFileId implements Serializable {

  private Portfolio portfolio;
  private File file;
}
