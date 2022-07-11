package com.savvycom.orderservice.domain.output;

import com.savvycom.orderservice.domain.entity.Staff;
import com.savvycom.orderservice.domain.entity.Status;
import com.savvycom.orderservice.domain.input.OrderItemInput;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:32 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class OrderByIdOutput {
    private Long orderId;

    private Integer paymentId;

    private String receiverName;

    private String receiverAddress;

    private String phoneNumber;

    private Double totalPrice;

    private Status status;

    private Staff staff;

    private List<OrderItemInput> orderItems;
}
