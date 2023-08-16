package com.konkuk.soar.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.catalina.connector.Response;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {
  SUCCESS(true, Response.SC_OK, "요청에 성공하였습니다."),
  NOT_FOUND(false, Response.SC_NOT_FOUND, "Not found"),
  INTERNAL_SERVER_ERROR(false, Response.SC_INTERNAL_SERVER_ERROR, "Server Error"),;

  private final boolean isSuccess;
  private final int status;
  private final String message;
}