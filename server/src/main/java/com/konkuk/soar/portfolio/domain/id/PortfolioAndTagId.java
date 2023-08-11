package com.konkuk.soar.portfolio.domain.id;

import com.konkuk.soar.common.domain.Tag;
import com.konkuk.soar.portfolio.domain.portfolio.Portfolio;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
public class PortfolioAndTagId implements Serializable {

    private Portfolio portfolio;
    private Tag tag;
}
