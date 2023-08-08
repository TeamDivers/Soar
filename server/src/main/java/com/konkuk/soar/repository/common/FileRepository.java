package com.konkuk.soar.repository.common;

import com.konkuk.soar.domain.common.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
