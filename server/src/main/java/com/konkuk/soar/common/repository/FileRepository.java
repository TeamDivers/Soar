package com.konkuk.soar.common.repository;

import com.konkuk.soar.common.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
