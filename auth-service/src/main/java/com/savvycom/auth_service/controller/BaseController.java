package com.savvycom.auth_service.controller;
import com.savvycom.auth_service.common.Const;
import com.savvycom.auth_service.domain.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Định nghĩa kiểu trả về của controller
 */
public abstract class BaseController {

    /**
     * Thành công
     * @param data
     * @param successMessage
     * @return
     * @param <T>
     */
    protected <T> ResponseEntity<?> toSuccessResult(T data, String successMessage) {
        ResponseMessage<T> message = new ResponseMessage<>();

        message.setCode(Const.API_RESPONSE.API_STATUS_OK_STR + "");
        message.setSuccess(Const.API_RESPONSE.RESPONSE_STATUS_TRUE);
        message.setMessage(successMessage);
        message.setData(data);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Thất bại
     * @param errorMessage
     * @param code
     * @return
     * @param <T>
     */
    protected <T> ResponseEntity<?> toExceptionResult(String errorMessage, int code) {
        ResponseMessage<T> message = new ResponseMessage<>();

        message.setSuccess(Const.API_RESPONSE.RESPONSE_STATUS_FALSE);
        message.setCode(code + "");
        message.setMessage(errorMessage);

        return new ResponseEntity<>(message, HttpStatus.valueOf(code));
    }
}
