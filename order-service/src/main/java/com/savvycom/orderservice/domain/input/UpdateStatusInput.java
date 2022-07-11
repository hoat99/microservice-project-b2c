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
public class UpdateStatusInput {

    private @NotNull(message = "Vui lòng chọn trạng thái!") String statusName;

}
