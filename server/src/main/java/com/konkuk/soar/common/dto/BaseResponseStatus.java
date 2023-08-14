package com.konkuk.soar.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {
  SUCCESS(true, 200, "요청에 성공하였습니다.");

  private final boolean isSuccess;
  private final int status;
  private final String message;
}
