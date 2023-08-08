package com.konkuk.soar.portfolio.domain.project;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @ Project 도메인과 연관된 엔티티들의 정상 동작 여부 확인
 * @ 1. Cascade.REMOVE
 * @ 2. Cascade.PERSIST
 * @ 3. 각 도메인 별 OneToMany 매핑만 확인
 */
@DataJpaTest
@ActiveProfiles("test")
public class ProjectTest {

}
