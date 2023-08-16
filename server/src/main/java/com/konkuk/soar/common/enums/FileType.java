package com.konkuk.soar.common.enums;

public enum FileType {
  TIMELAPSE("timelapse"),
  NORMAL("normal");

  private final String name;

  FileType(String name) {
    this.name = name;
  }

  public static FileType of(String name) {
    for (FileType fileType : FileType.values()) {
      if (fileType.name.equalsIgnoreCase(name)) {
        return fileType;
      }
    }
    throw new IllegalArgumentException("NO FileType found for name:" + name);
  }
}