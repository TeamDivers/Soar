package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioCreateLargeDto {

  PortfolioCreateDto portfolio;
  List<ProjectCreateDto> projects;
}
