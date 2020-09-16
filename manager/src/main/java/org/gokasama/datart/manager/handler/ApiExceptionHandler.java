package org.gokasama.datart.manager.handler;

import lombok.extern.slf4j.Slf4j;
import org.gokasama.datart.manager.enums.ApiExceptionCode;
import org.gokasama.datart.manager.exception.ApiException;
import org.gokasama.datart.manager.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ka wujia@chinamobile.com
 */
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ApiResponse<?> defaultErrorHandler(HttpServletRequest request, Exception exception) {

        ApiExceptionCode apiExceptionCode;
        HttpStatus httpStatus;

        if (exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            return ApiResponse.fail(apiException.getCode(), apiException.getError(), apiException.getMessage(), null);
        } else {
            if (exception instanceof NoHandlerFoundException) {
                httpStatus = HttpStatus.NOT_FOUND;
            } else if (exception instanceof HttpClientErrorException.BadRequest) {
                httpStatus = HttpStatus.BAD_REQUEST;
            } else if (exception instanceof HttpClientErrorException.Forbidden) {
                httpStatus = HttpStatus.FORBIDDEN;
            } else if (exception instanceof HttpClientErrorException.NotFound) {
                httpStatus = HttpStatus.NOT_FOUND;
            } else if (exception instanceof HttpClientErrorException.Unauthorized) {
                httpStatus = HttpStatus.UNAUTHORIZED;
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            return ApiResponse.fail(httpStatus.value(), httpStatus.getReasonPhrase(), exception.getMessage(), null);
        }
    }
}
