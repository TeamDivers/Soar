package com.konkuk.soar.common.repository;

import com.konkuk.soar.common.domain.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

  Optional<Tag> findByName(String name);

}
