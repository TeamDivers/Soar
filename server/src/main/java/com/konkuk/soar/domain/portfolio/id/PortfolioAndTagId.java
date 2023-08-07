package com.konkuk.soar.domain.portfolio.id;

import com.konkuk.soar.domain.common.Tag;
import com.konkuk.soar.domain.portfolio.Portfolio;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PortfolioAndTagId implements Serializable {
    private Portfolio portfolio;
    private Tag tag;
}
