package com.konkuk.soar.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
  private final String accessToken;
  private final String refreshToken;
}