package com.konkuk.soar.global.exception;

public class NotFoundException extends RuntimeException {

  public static NotFoundException MEMBER_NOT_FOUND = new NotFoundException(
      "Invalid ID : Member Not Found");
  public static NotFoundException STUDY_HISTORY_NOT_FOUND = new NotFoundException(
      "Invalid ID : StudyHistoy Not Found");

  public NotFoundException(String message) {
    super(message);
  }
}
