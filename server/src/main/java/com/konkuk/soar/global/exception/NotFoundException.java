package com.konkuk.soar.global.exception;

public class NotFoundException extends RuntimeException {
  public static NotFoundException MEMBER_NOT_FOUND = new NotFoundException();
}
