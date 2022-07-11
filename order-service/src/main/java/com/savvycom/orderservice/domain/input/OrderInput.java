package com.savvycom.orderservice.domain.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:31 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class OrderInput {

    private @NotNull(message = "Chọn phương thức thanh toán") Integer paymentId;

    @Size(min = 1,max = 25, message = "receiver name should have at large 25 characters")
    private @NotNull(message = "Vui lòng nhập họ tên người nhận!") String receiverName;

    private @NotNull(message = "Vui lòng nhập địa chỉ người nhận!") String receiverAddress;

    private @NotNull(message = "Vui lòng nhập số điện thoại") String phoneNumber;

    private @NotNull(message = "Chọn nhân viên sale") String staffName;
}
