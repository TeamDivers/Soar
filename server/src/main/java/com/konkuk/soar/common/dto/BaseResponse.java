package com.konkuk.soar.common.dto;


import static com.konkuk.soar.common.dto.BaseResponseStatus.SUCCESS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@JsonPropertyOrder({"success", "status", "message", "result"})
@Schema(description = "기본 응답 dto format")
public class BaseResponse<T> {

  @Schema(description = "요청이 정상적으로 처리가 됐는지 여부")
  @JsonProperty("success")
  private final Boolean isSuccess;
  @Schema(description = "http status")
  private final int status;
  @Schema(description = "응답 메시지")
  private final String message;
  @Schema(description = "요청 결과")
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