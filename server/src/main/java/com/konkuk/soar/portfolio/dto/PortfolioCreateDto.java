package com.konkuk.soar.portfolio.dto;

import com.konkuk.soar.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCreateDto {

    private String title;
    private String description;
    private String category;
    private boolean isPublic;
    private Member member;
}
