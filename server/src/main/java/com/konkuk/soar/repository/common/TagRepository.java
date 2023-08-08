package com.konkuk.soar.repository.common;

import com.konkuk.soar.domain.common.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
