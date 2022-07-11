package com.savvycom.orderservice.domain.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Create by Nam Ga Sky
 * Date: 6/15/2022
 * Time: 11:20 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseMessage {
    private String code;
    private Boolean success;
    private String message;
    private String description;
}
