package com.savvycom.orderservice.controller;

import com.savvycom.orderservice.controller.BaseController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

/**
 * Create by Nam Ga Sky
 * Date: 6/15/2022
 * Time: 10:43 AM
 * Project Name:  order-service
 */
@ControllerAdvice
public class ControllerExceptionHandler extends BaseController {
    /**
     * Nơi xử lý lỗi từ các Exception của Controller khi truyền tới
     * @param e
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException e) {
        return createFailureResponse(
                HttpStatus.BAD_REQUEST.value() + "",
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage(),
                null);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        return createFailureResponse(
                HttpStatus.BAD_REQUEST.value() + "",
                e.getMessage(),
                null);
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleException(Exception e) {
        return createFailureResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value() + "",
                e.getMessage(),
                null);
    }
}
