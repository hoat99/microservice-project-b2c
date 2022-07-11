package com.savvycom.orderservice.exception;

/**
 * Create by Nam Ga Sky
 * Date: 6/25/2022
 * Time: 10:20 PM
 * Project Name:  order-service
 */
public class OrderNotFoundException extends IllegalArgumentException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}