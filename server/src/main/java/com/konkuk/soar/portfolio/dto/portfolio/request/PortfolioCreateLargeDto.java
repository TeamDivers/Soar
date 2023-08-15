package com.konkuk.soar.portfolio.dto.portfolio.request;

import com.konkuk.soar.portfolio.dto.project.request.ProjectCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "포트폴리오 생성 시 프로젝트를 같이 생성할 경우 필요한 request body dto")
public class PortfolioCreateLargeDto {

  @Schema(name = "생성할 포트폴리오")
  PortfolioCreateDto portfolio;
  @Schema(name = "생성할 프로젝트")
  List<ProjectCreateDto> projects;
}
