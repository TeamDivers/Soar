package com.konkuk.soar.portfolio.enums;

public enum OptionType {
  NEWEST("newest"),
  OLDEST("oldest"),
  RANK("rank"),
  PUBLIC("public"),
  PRIVATE("private");

  private final String name;

  OptionType(String name) {
    this.name = name;
  }

  public static OptionType of(String name) {
    for (OptionType optionType : OptionType.values()) {
      if (optionType.name.equalsIgnoreCase(name)) {
        return optionType;
      }
    }
    throw new IllegalArgumentException("NO OptionType found for name:" + name);
  }
}
