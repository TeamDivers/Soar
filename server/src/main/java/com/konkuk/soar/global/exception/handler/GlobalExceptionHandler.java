package com.konkuk.soar.global.exception.handler;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.common.dto.BaseResponseStatus;
import com.konkuk.soar.global.exception.ExceptionResult;
import com.konkuk.soar.global.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({NotFoundException.class})
  protected BaseResponse<ExceptionResult> handleNotFoundException(NotFoundException e) {
    BaseResponseStatus status = BaseResponseStatus.NOT_FOUND;
    return BaseResponse.fail(status, new ExceptionResult(e.getMessage()));
  }

  @ExceptionHandler({RuntimeException.class})
  protected BaseResponse<ExceptionResult> handleDefaultException(RuntimeException e) {
    BaseResponseStatus status = BaseResponseStatus.INTERNAL_SERVER_ERROR;
    return BaseResponse.fail(status, new ExceptionResult(e.getMessage()));
  }


}
