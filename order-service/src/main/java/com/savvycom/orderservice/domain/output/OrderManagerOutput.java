package com.savvycom.orderservice.domain.output;

import com.savvycom.orderservice.domain.entity.Status;
import com.savvycom.orderservice.domain.input.OrderItemInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/4/2022
 * Time: 10:07 AM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class OrderManagerOutput {
    private Long orderId;

    private LocalDateTime createAt;

    private Status status;

    private Double totalPrice;

    private List<OrderItemInput> orderItemInputs;
}
