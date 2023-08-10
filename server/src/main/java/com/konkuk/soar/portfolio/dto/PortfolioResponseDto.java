package com.konkuk.soar.portfolio.dto;

import com.konkuk.soar.portfolio.domain.project.Project;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
특정 Portfolio 조회 요청에 대한 응답 DTO
 */
@Data
@NoArgsConstructor
public class PortfolioResponseDto {

    private Long portfolio_id;
    private String title;
    private String description;
    private String category;
    private Long member_id;

    private List<Project> projects;


}
