package com.konkuk.soar.portfolio.dto.project.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Schema(description = "프로젝트를 생성할 때 필요한 request body dto")
public class ProjectCreateDto {

  @Schema(name = "프로젝트 제목")
  private String title;
  @Schema(name = "프로젝트 카테고리")
  private String category;
  @Schema(name = "프로젝트에서 맡은 역할")
  private String role;
  @Schema(name = "프로젝트 설명")
  private String description;
  @Schema(name = "프로젝트 시작 날짜")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime startDate;
  @Schema(name = "프로젝트 종료 날짜")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime endDate;
  @Schema(name = "해당 프로젝트에 넣을 배경 색")
  private String background;

  @Setter
  private Long portfolioId;
  @Schema(name = "해당 프로젝트에 첨부할 파일 리스트")
  private List<FileCreateDto> files;
  @Schema(name = "해당 프로젝트에 첨부할 학습 기록 리스트")
  private List<Long> histories;

}