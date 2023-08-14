package com.konkuk.soar.common.dto;


import static com.konkuk.soar.common.dto.BaseResponseStatus.SUCCESS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@JsonPropertyOrder({"success", "status", "message", "result"})
public class BaseResponse<T> {

  @JsonProperty("success")
  private final Boolean isSuccess;
  private final int status;
  private final String message;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T result;

  // 200
  protected BaseResponse(T result) {
    this.isSuccess = SUCCESS.isSuccess();
    this.status = SUCCESS.getStatus();
    this.message = SUCCESS.getMessage();
    this.result = result;
  }

  protected BaseResponse(BaseResponseStatus status, T result) {
    this.isSuccess = status.isSuccess();
    this.status = status.getStatus();
    this.message = status.getMessage();
    this.result = result;
  }

  public static <G> BaseResponse<G> success(G result) {
    return new BaseResponse<>(result);
  }

  public static <G> BaseResponse<G> fail(BaseResponseStatus status, G result) {
    return new BaseResponse<>(status, result);
  }
}
