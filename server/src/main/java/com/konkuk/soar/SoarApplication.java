package com.konkuk.soar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SoarApplication {

  public static void main(String[] args) {
    SpringApplication.run(SoarApplication.class, args);
  }

}
