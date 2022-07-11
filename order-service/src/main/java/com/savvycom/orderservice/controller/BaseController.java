package com.savvycom.orderservice.controller;

import com.savvycom.orderservice.domain.message.BaseMessage;
import com.savvycom.orderservice.domain.message.ExtendedMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Create by Nam Ga Sky
 * Date: 6/15/2022
 * Time: 2:23 PM
 * Project Name:  order-service
 */
public abstract class BaseController {
    /**
     * Nơi nhận dữ liệu truyền từ các Controller khi trả
     * về và khi được khai báo ở Controller.
     * @param message = thông báo
     * @param description = mô tả cho thông báo
     * @param data - dữ liệu in ra
     * @param <T> = một bảng có trong đó
     * @return trả ra các thông tin
     */
    public <T> ResponseEntity<?> createSuccessResponse(String message, String description, T data) {
        ExtendedMessage<T> responseMessage = new ExtendedMessage<>(
                HttpStatus.OK.value() + "",
                true,
                message,
                description,
                data);
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    public <T> ResponseEntity<?> createSuccessResponse(String message, T data) {
        return createSuccessResponse(message, null, data);
    }

    public <T> ResponseEntity<?> createSuccessResponse(T data) {
        return createSuccessResponse(null, null, data);
    }

    public ResponseEntity<?> createSuccessResponse() {
        return createSuccessResponse(null, null, null);
    }

    /**
     * Tương tự ở trên và đây xử lý các hàm trả về failuer response
     * @param code = trạng thái trả ra {200,400,500}
     * @param message = thông báo
     * @param description = mô tả cho thông báo
     * @return
     */
    public ResponseEntity<?> createFailureResponse(String code, String message, String description) {
        BaseMessage responseMessage = new BaseMessage(
                code,
                false,
                message,
                description);
        return new ResponseEntity<>(responseMessage, HttpStatus.valueOf(Integer.parseInt(code)));
    }
}
