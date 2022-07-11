package com.savvycom.orderservice.domain.input;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Create by Nam Ga Sky
 * Date: 7/2/2022
 * Time: 4:34 PM
 * Project Name:  order-service
 */
@Getter
@Setter
@Builder
public class StaffInput {

    private @NotNull(message = "Vui lòng nhập tên của nhân viên!") String staffName;

}
