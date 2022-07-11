package com.savvycom.product_service.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.savvycom.product_service.common.Const;
import com.savvycom.product_service.domain.message.ResponseMessage;

/**
 * Kiểu trả về ở controller
 */
public abstract class BaseController {


    protected <T> ResponseEntity<?> toSuccessResult(T data, String successMessage) {
        ResponseMessage<T> message = new ResponseMessage<>();

        message.setCode(Const.API_RESPONSE.API_STATUS_OK_STR + "");
        message.setSuccess(Const.API_RESPONSE.RESPONSE_STATUS_TRUE);
        message.setMessage(successMessage);
        message.setData(data);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    protected <T> ResponseEntity<?> toExceptionResult(String errorMessage, int code) {
        ResponseMessage<T> message = new ResponseMessage<>();

        message.setSuccess(Const.API_RESPONSE.RESPONSE_STATUS_FALSE);
        message.setCode(code + "");
        message.setMessage(errorMessage);

        return new ResponseEntity<>(message, HttpStatus.valueOf(code));
    }
}
