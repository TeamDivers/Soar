package com.konkuk.soar.global.exception;

import lombok.Getter;

@Getter
public class ExceptionResult {

  private final String errMsg;
  public ExceptionResult(String errMsg) {
    this.errMsg = errMsg;
  }
}
