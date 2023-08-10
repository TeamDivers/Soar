package com.konkuk.soar.portfolio.dto;

import com.konkuk.soar.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PortfolioCreateDto {

    private String title;
    private String description;
    private String category;
    private boolean isPublic;
    private Long memberId;

    @Builder
    public PortfolioCreateDto(String title, String description, String category, boolean isPublic, Member member) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.isPublic = isPublic;
        this.memberId = member.getId();
    }
}
