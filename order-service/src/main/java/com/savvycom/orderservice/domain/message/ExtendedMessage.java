package com.savvycom.orderservice.domain.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create by Nam Ga Sky
 * Date: 6/15/2022
 * Time: 11:21 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedMessage<T> extends BaseMessage{
    private T data;

    public ExtendedMessage(String code, Boolean success, String message, String description, T data) {
        super(code, success, message, description);
        this.data = data;
    }
}
