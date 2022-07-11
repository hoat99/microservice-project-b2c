package com.savvycom.orderservice.domain.input;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Create by Nam Ga Sky
 * Date: 7/3/2022
 * Time: 4:14 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class UpdateReceiverInput {

    private @NotNull(message = "Vui lòng họ tên người nhận!") String receiverName;

    private @NotNull(message = "Vui lòng nhập địa chỉ người nhận!") String receiverAddress;

    private @NotNull(message = "Vui lòng nhập số điện thoại") String phoneNumber;
}
