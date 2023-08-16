package com.konkuk.soar.global.exception;

public class NotFoundException extends RuntimeException {

  public static NotFoundException MEMBER_NOT_FOUND = new NotFoundException(
      "Invalid ID : Member Not Found");
  public static NotFoundException STUDY_HISTORY_NOT_FOUND = new NotFoundException(
      "Invalid ID : StudyHistory Not Found");
  public static NotFoundException PORTFOLIO_NOT_FOUND = new NotFoundException(
      "Invalid ID : Portfolio Not Found");
  public static NotFoundException PROJECT_NOT_FOUND = new NotFoundException(
      "Invalid ID : Project Not Found");
  public static NotFoundException FILE_NOT_FOUND = new NotFoundException(
      "Invalid ID : File Not Found");

  public NotFoundException(String message) {
    super(message);
  }
}
